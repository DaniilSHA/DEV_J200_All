package com.example.labproject.servlets;

import com.example.labproject.ejb.DemoDOM;
import com.example.labproject.ejb.Transformer;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CheckDOM", value = "/checkdom")
public class CheckDOM extends HttpServlet {

    @EJB
    private Transformer transformer;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String model = request.getParameter("model");
        DemoDOM demoDOM = new DemoDOM(transformer.transform(), model == null ? "" : model, response);
        demoDOM.find();
    }
}
