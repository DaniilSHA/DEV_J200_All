package com.example.labproject.servlets;

import com.example.labproject.ejb.Transformer;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CheckSAX", value = "/checksax")
public class CheckSAX extends HttpServlet {

    @EJB
    private Transformer transformer;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        transformer.transform();

    }
}
