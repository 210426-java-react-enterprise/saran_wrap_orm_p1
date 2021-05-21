package com.revature.project1;

import com.revature.project1.dbentry.SqlCreation;
import com.revature.project1.dbentry.SqlInsert;
import com.revature.project1.models.AppUser;

public class ORMDriver {
    public static void main(String[] args) throws IllegalAccessException {

        Object userObj = new AppUser("gtomasel", "Passw0rd", "Email@mail.com", "Giancarlo", "Lastname", 23);
        SqlInsert insertTest = new SqlInsert();

        insertTest.insertNewObject(userObj);
    }
}
