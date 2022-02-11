package psuti.kirgizov.servlet;

import psuti.kirgizov.phone.Phone;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

// Maven
// tomcat7:run

// localhost:8088

public class PhoneServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) {
        List<Phone> phoneList;
        try {
            phoneList = PhoneService.service(request, response);
        } catch (Exception e) {
            e.printStackTrace();

            phoneList = new ArrayList<>();
        }

        try {
            request.setAttribute("phonelist", phoneList);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
            requestDispatcher.include(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}