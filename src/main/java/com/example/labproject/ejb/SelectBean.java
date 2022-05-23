package com.example.labproject.ejb;

import com.example.labproject.jpa.DbManager;
import com.example.labproject.models.Address;
import com.example.labproject.storage.ClientStorage;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.stream.Collectors;

@Stateless
public class SelectBean {

    @EJB
    private DbManager dbManager;


    public void findAllClients(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("clientList", dbManager.loadAllClients());
        request.getRequestDispatcher("/view-list.jsp").forward(request, response);
    }

    public void findAllUseFilter(
            HttpServletRequest request,
            HttpServletResponse response,
            String city,
            String street,
            String num
    ) throws ServletException, IOException {
        if (city.length() > 101) {
            request.setAttribute("errorField", "город фильтра");
            request.setAttribute("errorReason", "полее превышает допустимую длину");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return;
        } else if (city.replaceAll("[a-zA-Z]", "").length() != city.length()) {
            request.setAttribute("errorField", "город фильтра");
            request.setAttribute("errorReason", "полее содержит латинский алфавит");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return;
        }

        if (street.length() > 101) {
            request.setAttribute("errorField", "улица фильтра");
            request.setAttribute("errorReason", "полее превышает допустимую длину");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return;
        } else if (street.replaceAll("[a-zA-Z]", "").length() != street.length()) {
            request.setAttribute("errorField", "улица фильтра");
            request.setAttribute("errorReason", "полее содержит латинский алфавит");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return;
        }

        try {
            if (!num.trim().equals("")) Integer.parseInt(num);
        } catch (ClassCastException e) {
            request.setAttribute("errorField", "номер дома фильтра");
            request.setAttribute("errorReason", "не число");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return;
        }

        request.setAttribute("clientList",
                dbManager.loadAllClients().stream()
                        .filter(Objects::nonNull)
                        .filter(client ->
                                client.getAddressList().contains(
                                        client.getAddressList().stream().filter(
                                                address -> (city.trim().equals("") || address.getCity().equals(city)) &&
                                                        (street.trim().equals("") || address.getStreet().equals(street)) &&
                                                        (num.trim().equals("") || address.getNum() == Integer.parseInt(num))
                                        ).findFirst().orElse(new Address()))
                        )
                        .collect(Collectors.toList()));
        request.getRequestDispatcher("/view-list.jsp").forward(request, response);
    }
}
