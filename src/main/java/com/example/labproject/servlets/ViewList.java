package com.example.labproject.servlets;

import com.example.labproject.models.Address;
import com.example.labproject.models.Client;
import com.example.labproject.storage.ClientStorage;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@WebServlet(name = "ViewList", value = "/viewlist")
public class ViewList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("clientList", ClientStorage.CLIENT_LIST);
        request.getRequestDispatcher("/view-list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String city = request.getParameter("cityFilter");
        String street = request.getParameter("streetFilter");
        String num = request.getParameter("numFilter");


        if (city.length()>101) {
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

        if (street.length()>101) {
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
            if(!num.trim().equals("")) Integer.parseInt(num);
        } catch (ClassCastException e) {
            request.setAttribute("errorField", "номер дома фильтра");
            request.setAttribute("errorReason", "не число");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return;
        }

              request.setAttribute("clientList",
                ClientStorage.CLIENT_LIST.stream()
                        .filter(Objects::nonNull)
                        .filter(client ->
                                client.getAddressList().contains(
                                        client.getAddressList().stream().filter(
                                                        address ->  (city.trim().equals("") || address.getCity().equals(city)) &&
                                                                    (street.trim().equals("") || address.getStreet().equals(street)) &&
                                                                    (num.trim().equals("") || address.getNum() == Integer.parseInt(num))
                                                        ).findFirst().orElse(new Address()))
                        )
                        .collect(Collectors.toList()));
        request.getRequestDispatcher("/view-list.jsp").forward(request, response);
    }
}
