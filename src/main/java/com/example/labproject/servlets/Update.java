package com.example.labproject.servlets;

import com.example.labproject.models.Address;
import com.example.labproject.models.Client;
import com.example.labproject.storage.ClientStorage;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Pattern;

@WebServlet(name = "Update", value = "/update")
public class Update extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/update.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String id = request.getParameter("clientId");

        try {
            int castId = Integer.parseInt(id);
            if (ClientStorage.CLIENT_LIST.get(--castId) == null) throw new IndexOutOfBoundsException("user don't found");
        } catch (ClassCastException e) {
            request.setAttribute("errorField", "id");
            request.setAttribute("errorReason", "поле не число");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return;
        } catch (IndexOutOfBoundsException e) {
            request.setAttribute("errorField", "id");
            request.setAttribute("errorReason", "пользователь с таким id не найден");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return;
        }

        String type = request.getParameter("clientType");
        String model = request.getParameter("clientModel");
        String ip = request.getParameter("clientIp");

        String city = request.getParameter("adrCity");
        String street = request.getParameter("adrStreet");
        String num = request.getParameter("adrHomeNumber");
        String subnum = request.getParameter("adrSubNumber");
        String flat = request.getParameter("adrFlatNumber");
        String extra = request.getParameter("adrExtra");


        boolean validateClientResult = validateClient(request, response, type, model, ip);
        boolean validateAddressResult = validateAddress(request, response, city, street, num, subnum, flat, extra);


        if (validateAddressResult && validateClientResult) {

            ClientStorage.CLIENT_LIST.remove(Integer.parseInt(id)-1);
            ClientStorage.CLIENT_LIST.add(
                    Integer.parseInt(id)-1,
                    new Client(
                            Integer.parseInt(id),
                            type,
                            model,
                            ip,
                            new ArrayList<>(Collections.singletonList(
                                    new Address(
                                            ++ClientStorage.ADDRESS_ID_COUNTER,
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
        request.setAttribute("clientList", ClientStorage.CLIENT_LIST);
        request.getRequestDispatcher("/view-list.jsp").forward(request, response);
    }

    private boolean validateClient (HttpServletRequest request, HttpServletResponse response,
                                    String type, String model, String ip) throws ServletException, IOException {

        if (type.trim().equals("")) {
            request.setAttribute("errorField", "тип устройства");
            request.setAttribute("errorReason", "поле не может быть пустыми");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return false;
        } else if (type.length()>101) {
            request.setAttribute("errorField", "тип устройства");
            request.setAttribute("errorReason", "полее превышает допустимую длину");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return false;
        } else if (type.replaceAll("[a-zA-Z]", "").length() != type.length()) {
            request.setAttribute("errorField", "тип устройства");
            request.setAttribute("errorReason", "полее содержит латинский алфавит");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return false;
        }

        if (model.trim().equals("")) {
            request.setAttribute("errorField", "модель");
            request.setAttribute("errorReason", "поле не может быть пустыми");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return false;
        } else if (model.length()>101) {
            request.setAttribute("errorField", "модель");
            request.setAttribute("errorReason", "полее превышает допустимую длину");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return false;
        } else if (model.replaceAll("[a-zA-Z]", "").length() != model.length()) {
            request.setAttribute("errorField", "модель");
            request.setAttribute("errorReason", "полее содержит латинский алфавит");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return false;
        }


        if (ip.trim().equals("")) {
            request.setAttribute("errorField", "ip");
            request.setAttribute("errorReason", "поле не может быть пустыми");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return false;
        } else if (ip.length()>101) {
            request.setAttribute("errorField", "ip");
            request.setAttribute("errorReason", "полее превышает допустимую длину");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return false;
        } else if (!Create.IpAddressValidator.isValid(ip)) {
            request.setAttribute("errorField", "ip");
            request.setAttribute("errorReason", "полее не соответвует маски ip");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return false;
        }

        return true;
    }

    private boolean validateAddress (HttpServletRequest request, HttpServletResponse response,
                                     String city, String street,
                                     String num, String subnum,
                                     String flat, String extra) throws ServletException, IOException {

        if (city.trim().equals("")) {
            request.setAttribute("errorField", "город");
            request.setAttribute("errorReason", "поле не может быть пустыми");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return false;
        } else if (city.length()>101) {
            request.setAttribute("errorField", "город");
            request.setAttribute("errorReason", "полее превышает допустимую длину");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return false;
        } else if (city.replaceAll("[a-zA-Z]", "").length() != city.length()) {
            request.setAttribute("errorField", "город");
            request.setAttribute("errorReason", "полее содержит латинский алфавит");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return false;
        }

        if (street.trim().equals("")) {
            request.setAttribute("errorField", "улица");
            request.setAttribute("errorReason", "поле не может быть пустыми");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return false;
        } else if (street.length()>101) {
            request.setAttribute("errorField", "улица");
            request.setAttribute("errorReason", "полее превышает допустимую длину");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return false;
        } else if (street.replaceAll("[a-zA-Z]", "").length() != street.length()) {
            request.setAttribute("errorField", "улица");
            request.setAttribute("errorReason", "полее содержит латинский алфавит");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return false;
        }

        if (extra.trim().equals("")) {
            request.setAttribute("errorField", "доп. информация");
            request.setAttribute("errorReason", "поле не может быть пустыми");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return false;
        } else if (extra.length()>201) {
            request.setAttribute("errorField", "доп. информация");
            request.setAttribute("errorReason", "полее превышает допустимую длину");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return false;
        } else if (extra.replaceAll("[a-zA-Z]", "").length() != extra.length()) {
            request.setAttribute("errorField", "доп. информация");
            request.setAttribute("errorReason", "полее содержит латинский алфавит");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return false;
        }

        if (num.trim().equals("")) {
            request.setAttribute("errorField", "номер дома");
            request.setAttribute("errorReason", "поле не может быть пустыми");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return false;
        }
        try {
            Integer.parseInt(num);
        } catch (ClassCastException e) {
            request.setAttribute("errorField", "номер дома");
            request.setAttribute("errorReason", "не число");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return false;
        }

        if (subnum.trim().equals("")) {
            request.setAttribute("errorField", "корпус");
            request.setAttribute("errorReason", "поле не может быть пустыми");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return false;
        }
        try {
            Integer.parseInt(subnum);
        } catch (ClassCastException e) {
            request.setAttribute("errorField", "корпус");
            request.setAttribute("errorReason", "не число");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return false;
        }

        if (flat.trim().equals("")) {
            request.setAttribute("errorField", "квартира");
            request.setAttribute("errorReason", "поле не может быть пустыми");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return false;
        }
        try {
            Integer.parseInt(flat);
        } catch (ClassCastException e) {
            request.setAttribute("errorField", "квартира");
            request.setAttribute("errorReason", "не число");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return false;
        }

        return true;
    }

    static class IpAddressValidator {
        private static final String zeroTo255
                = "([01]?[0-9]{1,2}|2[0-4][0-9]|25[0-5])";

        private static final String IP_REGEXP
                = zeroTo255 + "\\." + zeroTo255 + "\\."
                + zeroTo255 + "\\." + zeroTo255;

        private static final Pattern IP_PATTERN
                = Pattern.compile(IP_REGEXP);

        public static boolean isValid(String address) {
            return IP_PATTERN.matcher(address).matches();
        }
    }
}
