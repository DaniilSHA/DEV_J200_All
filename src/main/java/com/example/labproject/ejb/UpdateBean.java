package com.example.labproject.ejb;

import com.example.labproject.servlets.Create;

import javax.ejb.Stateless;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Stateless
public class UpdateBean {


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
            if (!Create.IpAddressValidator.isValid(param)) {
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

