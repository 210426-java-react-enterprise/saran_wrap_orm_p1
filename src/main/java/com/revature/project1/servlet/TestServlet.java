package com.revature.project1.servlet;

import com.revature.project1.models.AppUser;
import com.revature.project1.services.SaranServices;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        SaranServices saranwrap = new SaranServices();
        Object userObj = new AppUser("cTest", "Passw0rd", "cTest@mail.com", "Calex", "Tester", 23);
        String str = saranwrap.selectAllDB(userObj);
        resp.getWriter().write("<h1>/Selected all from DB String Success!</h1>");
        resp.getWriter().write(str);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        SaranServices saranwrap = new SaranServices();
        Object userObj = new AppUser("cTest", "Passw0rd", "cTest@mail.com", "Calex", "Tester", 23);

        String str = saranwrap.insertInDB(userObj);
        resp.getWriter().write("<h1>/Inserted Into DB String Success!</h1>");
        resp.getWriter().write(str);
    }

}
