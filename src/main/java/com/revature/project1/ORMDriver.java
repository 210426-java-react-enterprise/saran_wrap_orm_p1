package com.revature.project1;

import com.revature.project1.dbentry.*;
import com.revature.project1.models.AppUser;
import com.revature.project1.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class ORMDriver {
    public static void main(String[] args) throws IllegalAccessException {

        Object userObj = new AppUser("gtomasel", "Passw0rd", "Email@mail.com", "Giancarlo", "Lastname", 23);
        SqlInsert insertTest = new SqlInsert();
//        insertTest.setStatement(userObj);
//        insertTest.getStatement();

        //INSERT
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            insertTest.insertNewObject(userObj, conn);
        } catch (SQLException e){
            e.printStackTrace();
        }

        //SELECT
        SqlSelect selectTest = new SqlSelect();
        selectTest.setStatement(userObj, "username=gtomasel, password=Passw0rd");
        selectTest.getStatement();

        //UPDATE
        SqlUpdate updateTest = new SqlUpdate();
        updateTest.setStatement(userObj, "username=gtomasel");
        updateTest.getStatement();

        //DELETE
        SqlDelete deleteTest = new SqlDelete();
        deleteTest.setStatement(userObj, "username=gtomasel");
        deleteTest.getStatement();

    }
}
