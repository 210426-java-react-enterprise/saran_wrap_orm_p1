package com.revature.project1.dbentry;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlSelect extends SqlCrud {
    public SqlSelect() {
        action = "select";
    }

    public void selectAll(Object obj, Connection conn) throws IllegalAccessException {

        setStatement(obj);

        Statement stmt = null;

        try {
            stmt = conn.createStatement();
            stmt.executeQuery(statement);
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }

        System.out.println("running select command");
    }
}
