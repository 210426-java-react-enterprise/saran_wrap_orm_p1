package com.revature.project1.dbentry;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class SqlInsert extends StatementCreation {
    public SqlInsert() {
        action = "insert into";
    }


    public void insertNewObject(Object obj, Connection conn) {

        Statement stmt = null;
        try {

            setStatement(obj);
            stmt = conn.createStatement();

            int rowsInserted = stmt.executeUpdate(statement);
            ResultSet rs = stmt.getGeneratedKeys();
            //System.out.println(rowsInserted);
        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
        }

        System.out.println("Ran SQL INSERT command");
        //Run SQL command with connection to persist data
    }
}
