package com.revature.project1.servlet;

import com.revature.project1.dbentry.SqlCreation;
import com.revature.project1.models.AppUser;
import com.revature.project1.services.SaranServices;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class TestServlet extends HttpServlet {

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException{

        SaranServices saranwrap = new SaranServices(new SqlCreation());


        String str = saranwrap.deleteObject(AppUser.class, "username = 'gtomasel'");
        resp.getWriter().write("<h1>/Delete DB obj Success!</h1>");
        resp.getWriter().write(str);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        SaranServices saranwrap = new SaranServices(new SqlCreation());

        ArrayList<AppUser> DBObjects = saranwrap.selectObject(AppUser.class, "username = 'cTest'");
        resp.getWriter().write("<h1>/Testing generic select specific!</h1>\n");
        for (AppUser user : DBObjects){
            resp.getWriter().write(user.toString() + " \n");
        }

        ArrayList<AppUser> allDBObject = saranwrap.selectAllObjects(AppUser.class);
        resp.getWriter().write("<h1>/Testing generic select all!</h1>\n");
        for (AppUser user : allDBObject){
            resp.getWriter().write(user.toString() + " \n");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        SaranServices saranwrap = new SaranServices(new SqlCreation());
        Object userObj = new AppUser("cTest", "Passw0rd", "cTest@mail.com", "Calex", "Tester", 23);

        String str = saranwrap.insertObject(userObj);
        resp.getWriter().write("<h1>/Inserted Into DB String Success!</h1>");
        resp.getWriter().write(str);
    }

    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        SaranServices saranwrap = new SaranServices(new SqlCreation());
        Object userObj = new AppUser("updateTester", "Passw0rd", "update@mail.com", "Upper", "Datelly", 18);

        String str = saranwrap.updateObject(userObj, "username = 'updateTester'");
        resp.getWriter().write("<h1>/Update Success!</h1>");
        resp.getWriter().write(str);
    }
}
