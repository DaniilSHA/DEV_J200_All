package com.example.labproject.servlets;

import com.example.labproject.ejb.SelectBean;
import com.example.labproject.models.Address;
import com.example.labproject.storage.ClientStorage;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.stream.Collectors;

@WebServlet(name = "ViewList", value = "/viewlist")
public class ViewList extends HttpServlet {

    @EJB
    private SelectBean selectBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        selectBean.findAllClients(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String city = request.getParameter("cityFilter");
        String street = request.getParameter("streetFilter");
        String num = request.getParameter("numFilter");

        selectBean.findAllUseFilter(request,response,city,street,num);
    }
}
