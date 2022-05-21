package com.example.labproject.servlets;

import com.example.labproject.ejb.UpdateBean;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Update", value = "/update")
public class Update extends HttpServlet {

    @EJB
    private UpdateBean updateBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/update.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String id = request.getParameter("clientId");
        String type = request.getParameter("clientType");
        String model = request.getParameter("clientModel");
        String ip = request.getParameter("clientIp");

        String city = request.getParameter("adrCity");
        String street = request.getParameter("adrStreet");
        String num = request.getParameter("adrHomeNumber");
        String subnum = request.getParameter("adrSubNumber");
        String flat = request.getParameter("adrFlatNumber");
        String extra = request.getParameter("adrExtra");

        updateBean.updateClient(request,response,id,type,model,ip,city,street,num,subnum,flat,extra);

    }
}
