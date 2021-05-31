package com.revature.project1.dbentry;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlUpdate extends StatementCreation {
    public SqlUpdate() {
        action = "update";
    }

    public void update(Object obj, Connection conn)  {
        Statement stmt = null;

        try {
            setStatement(obj, condition);
            stmt = conn.createStatement();
            stmt.executeUpdate(statement);
        } catch (IllegalAccessException | SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Ran SQL UPDATE command");
    }
}
