package com.revature.project1.servlet;

import com.revature.project1.models.AppUser;
import com.revature.project1.services.SaranServices;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Dispatcher {
    Dispatcher () {}

    public void dispatch (HttpServletRequest req, HttpServletResponse resp) throws IOException {
        switch (req.getRequestURI()) {
            // /registration.data, /authentication.data, /transaction.data, /delete.data
            // based on /pattern in web.xml file
            case "saranwarap/ConnectionTest":
                System.out.println("Running connection test");
                Object userObj = new AppUser("gtomasel", "Passw0rd", "Email@mail.com", "Giancarlo", "Lastname", 23);

                System.out.println("Attempting to create a connection");
                SaranServices saran = new SaranServices();

                System.out.println("Attempting To add to DB");
                saran.insertInDB(userObj);
                break;
            case "saranwrap/registration.data":
                break;
            case "saranwrap/authentication.data":
                break;
            case "saranwrap/transaction.data":
                break;
            case "saranwrap/delete.data":
                break;
            default:
                resp.setStatus(400);
                resp.getWriter().println("Invalid Request");
        }
    }
}
