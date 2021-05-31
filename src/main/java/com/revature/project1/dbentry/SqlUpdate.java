package com.revature.project1.dbentry;

import com.revature.project1.exception.ResourcePersistenceException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlUpdate extends StatementCreation {
    public SqlUpdate() {
        action = "update";
    }

    //Requires the setCondition(...) method to be called first
    public void update(Object obj, Connection conn)  {
        Statement stmt = null;

        try {
            setStatement(obj, condition);
            stmt = conn.createStatement();
            stmt.executeUpdate(statement);
        } catch (IllegalAccessException | SQLException e) {
            e.printStackTrace();
            throw new ResourcePersistenceException("Cannot run SQL UPDATE Command.");
        }
        System.out.println("Ran SQL UPDATE command");
    }

    public void update(Object obj, String condition, Connection conn) {
        this.condition = condition;
        Statement stmt = null;

        try {
            setStatement(obj, condition);
            stmt = conn.createStatement();
            stmt.executeUpdate(statement);
        } catch (IllegalAccessException | SQLException e) {
            e.printStackTrace();
            throw new ResourcePersistenceException("Cannot run SQL UPDATE Command.");
        }
        System.out.println("Ran SQL UPDATE command");
    }
}
