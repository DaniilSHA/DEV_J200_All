package com.example.labproject.servlets;

import com.example.labproject.ejb.DemoSAX;
import com.example.labproject.ejb.Transformer;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "CheckSAX", value = "/checksax")
public class CheckSAX extends HttpServlet {

    @EJB
    private Transformer transformer;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            String model = new String("д110".getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);

            transformer.transform();
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            InputSource inputSource = new InputSource(transformer.transform().getAbsolutePath());
            inputSource.setEncoding("UTF-8");
            saxParser.parse(inputSource, new DemoSAX(model, response, request));

        } catch (SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        }
    }
}
