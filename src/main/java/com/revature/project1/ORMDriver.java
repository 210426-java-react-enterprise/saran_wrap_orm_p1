package com.revature.project1;

import com.revature.project1.dbentry.SqlCreation;
import com.revature.project1.dbentry.SqlInsert;
import com.revature.project1.models.AppUser;
import com.revature.project1.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class ORMDriver {
    public static void main(String[] args) throws IllegalAccessException {

        Object userObj = new AppUser("gtomasel", "Passw0rd", "Email@mail.com", "Giancarlo", "Lastname", 23);
        SqlInsert insertTest = new SqlInsert();

        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            insertTest.insertNewObject(userObj, conn);
        } catch (SQLException e){
            e.printStackTrace();
        }



    }
}
