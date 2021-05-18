package com.revature.project1.servlet;

import com.revature.project1.models.AppUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Dispatcher {
    Dispatcher () {}

    public void dispatch (HttpServletRequest req, HttpServletResponse resp) throws IOException {
        switch (req.getRequestURI()) {
            // /registration.data, /authentication.data, /transaction.data, /delete.data
            // based on /pattern in web.xml file
            case "p0/registration.data":
                break;
            case "p0/authentication.data":
                break;
            case "p0/transaction.data":
                break;
            case "p0/delete.data":
                break;
            default:
                resp.setStatus(400);
                resp.getWriter().println("Invalid Request");
        }
    }
}
