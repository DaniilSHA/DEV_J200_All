package com.example.labproject.servlets;

import com.example.labproject.storage.ClientStorage;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "Delete", value = "/delete")
public class Delete extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("deletionId");

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

        ClientStorage.CLIENT_LIST.remove(Integer.parseInt(id)-1);
        ClientStorage.CLIENT_LIST.add(Integer.parseInt(id)-1, null);

        request.setAttribute("clientList", ClientStorage.CLIENT_LIST);
        request.getRequestDispatcher("/view-list.jsp").forward(request, response);
    }
}
