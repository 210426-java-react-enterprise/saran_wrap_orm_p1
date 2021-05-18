package com.revature.project1;

import com.revature.project1.dbentry.SqlCreation;
import com.revature.project1.dbentry.SqlInsert;
import com.revature.project1.models.AppUser;

public class ORMDriver {
    public static void main(String[] args) {

        Object userObj = new AppUser("username", "password", "email", "firstname", "lastname", 23);
        SqlInsert insertTest = new SqlInsert();

        insertTest.insertNewObject(userObj);
    }
}
