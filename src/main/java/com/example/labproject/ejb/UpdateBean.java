package com.example.labproject.ejb;

import com.example.labproject.exceptions.AddressNotBoundException;
import com.example.labproject.exceptions.ClientNotFoundException;
import com.example.labproject.jpa.DbManager;
import com.example.labproject.models.Address;
import com.example.labproject.models.Client;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

@Stateless
public class UpdateBean {

    @EJB
    private DbManager dbManager;

    public void deleteClient( HttpServletRequest request,
                              HttpServletResponse response,
                              String id) throws ServletException, IOException {

        try {
            dbManager.deleteClientById(Integer.parseInt(id));
            response.sendRedirect(request.getContextPath() + "/viewlist");
        } catch (ClassCastException e) {
            request.setAttribute("errorField", "id");
            request.setAttribute("errorReason", "поле не число");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        } catch (ClientNotFoundException e) {
            request.setAttribute("errorField", "id");
            request.setAttribute("errorReason", "пользователь с таким id не найден");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }


        public void updateClient(
                HttpServletRequest request,
                HttpServletResponse response,
                String idCleint,
                String type,
                String model,
                String ip,
                String idAddress,
                String city,
                String street,
                String num,
                String subnum,
                String flat,
                String extra
        ) throws ServletException, IOException {

            Client updatableClient = null;
            try {
                 updatableClient = dbManager.findClientById(Integer.parseInt(idCleint));
            } catch (ClassCastException e) {
                request.setAttribute("errorField", "id клиента");
                request.setAttribute("errorReason", "поле не число");
                request.getRequestDispatcher("/error.jsp").forward(request, response);
                return;
            } catch (ClientNotFoundException e) {
                request.setAttribute("errorField", "id");
                request.setAttribute("errorReason", "пользователь с таким id не найден");
                request.getRequestDispatcher("/error.jsp").forward(request, response);
                return;
            }

            try {
                Optional<Integer> updatableAddressId = updatableClient
                        .getAddressList()
                        .stream()
                        .map(Address::getIdAddress)
                        .filter(id -> id == Integer.parseInt(idAddress))
                        .findFirst();

                if (!updatableAddressId.isPresent()) throw new AddressNotBoundException(Integer.parseInt(idAddress));
            } catch (ClassCastException e) {
                request.setAttribute("errorField", "id адреса");
                request.setAttribute("errorReason", "поле не число");
                request.getRequestDispatcher("/error.jsp").forward(request, response);
                return;
            } catch (AddressNotBoundException e) {
                request.setAttribute("errorField", "id адреса");
                request.setAttribute("errorReason", "нету у данного пользователя");
                request.getRequestDispatcher("/error.jsp").forward(request, response);
                return;
            }

            boolean validateClientResult = validateClient(request, response, type, model, ip);
            boolean validateAddressResult = validateAddress(request, response, city, street, num, subnum, flat, extra);


            if (validateAddressResult && validateClientResult) {
                updatableClient.setType(type);
                updatableClient.setModel(model);
                updatableClient.setIp(ip);

                Address updatableAddress = updatableClient
                        .getAddressList()
                        .stream()
                        .filter(address -> address.getIdAddress() == Integer.parseInt(idAddress)).findFirst().get();

                updatableAddress.setCity(city);
                updatableAddress.setStreet(street);
                updatableAddress.setNum(Integer.parseInt(num));
                updatableAddress.setSubnum(Integer.parseInt(subnum));
                updatableAddress.setFlat(Integer.parseInt(flat));
                updatableAddress.setExtra(extra);

                dbManager.saveNewClient(updatableClient);

            } else return;
            response.sendRedirect(request.getContextPath() + "/viewlist");
        }

    public void createNewClient(
            HttpServletRequest request,
            HttpServletResponse response,
            String type,
            String model,
            String ip,
            String city,
            String street,
            String num,
            String subnum,
            String flat,
            String extra
    ) throws ServletException, IOException {
        boolean validateClientResult = validateClient(request, response, type, model, ip);
        boolean validateAddressResult = validateAddress(request, response, city, street, num, subnum, flat, extra);

        if (validateAddressResult && validateClientResult) {
            dbManager.saveNewClient(
                    new Client(
                            type,
                            model,
                            ip,
                            new ArrayList<>(Collections.singletonList(
                                    new Address(
                                            city,
                                            street,
                                            Integer.parseInt(num),
                                            Integer.parseInt(subnum),
                                            Integer.parseInt(flat),
                                            extra
                                    )
                            ))
                    )
            );
        } else return;
        response.sendRedirect(request.getContextPath() + "/viewlist");
    }

    private boolean validateClient (HttpServletRequest request, HttpServletResponse response,
                                    String type, String model, String ip) throws ServletException, IOException {
        validateStringParam(type, request, response, 101, "тип устройства", false);
        validateStringParam(model, request, response, 101, "модель", false);
        validateStringParam(ip, request, response, 26, "ip", true);
        return true;
    }

    private boolean validateAddress (HttpServletRequest request, HttpServletResponse response,
                                     String city, String street,
                                     String num, String subnum,
                                     String flat, String extra) throws ServletException, IOException {
        validateStringParam(city, request, response, 101, "город", false);
        validateStringParam(street, request, response, 101, "улица", false);
        validateStringParam(extra, request, response, 201, "доп. информация", false);
        validateNumberParam(num, request, response, "номер дома");
        validateNumberParam(subnum, request, response, "корпус");
        validateNumberParam(flat, request, response, "номер квартиры");
        return true;
    }

    private void validateStringParam(
            String param,
            HttpServletRequest request,
            HttpServletResponse response,
            int allowableLength,
            String errorField,
            boolean isIp
    ) throws ServletException, IOException {
        if (param.trim().equals("")) {
            request.setAttribute("errorField", errorField);
            request.setAttribute("errorReason", "поле не может быть пустыми");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        } else if (param.length() > allowableLength) {
            request.setAttribute("errorField", errorField);
            request.setAttribute("errorReason", "полее превышает допустимую длину");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        } else if (!isIp) {
            if (param.replaceAll("[a-zA-Z]", "").length() != param.length()) {
                request.setAttribute("errorField", errorField);
                request.setAttribute("errorReason", "полее содержит латинский алфавит");
                request.getRequestDispatcher("/error.jsp").forward(request, response);
            }
        } else {
            if (!IpAddressValidator.isValid(param)) {
                request.setAttribute("errorField", errorField);
                request.setAttribute("errorReason", "полее не соответвует маски ip");
                request.getRequestDispatcher("/error.jsp").forward(request, response);
            }
        }
    }

    private void validateNumberParam(
            String param,
            HttpServletRequest request,
            HttpServletResponse response,
            String errorField
    ) throws ServletException, IOException {
        if (param.trim().equals("")) {
            request.setAttribute("errorField", errorField);
            request.setAttribute("errorReason", "поле не может быть пустыми");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        try {
            Integer.parseInt(param);
        } catch (ClassCastException e) {
            request.setAttribute("errorField", errorField);
            request.setAttribute("errorReason", "не число");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        if (Integer.parseInt(param) <= 0) {
            request.setAttribute("errorField", errorField);
            request.setAttribute("errorReason", "не может быть отрицательным числом");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}

