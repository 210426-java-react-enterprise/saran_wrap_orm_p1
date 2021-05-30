package com.revature.project1.dbentry;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlDelete extends SqlCrud {
    public SqlDelete() {
        action = "delete from";
    }

    public <T> String delete(Class<T> obj, String condition, Connection conn){
        this.condition = condition;
        Statement stmt = null;

        try {
            setStatement(obj.newInstance());
            stmt = conn.createStatement();
            String rowsDeleted = String.valueOf(stmt.executeUpdate(statement));
            System.out.println("Ran SQL DELETE command.");
            return rowsDeleted;
        } catch (IllegalAccessException | InstantiationException | SQLException e) {
            e.printStackTrace();
        }

        return "Error";

    }

}
