package com.example.labproject.servlets;

import com.example.labproject.storage.ClientStorage;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ViewList", value = "/viewlist")
public class ViewList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("clientList", ClientStorage.CLIENT_LIST);
        request.getRequestDispatcher("/view-list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
