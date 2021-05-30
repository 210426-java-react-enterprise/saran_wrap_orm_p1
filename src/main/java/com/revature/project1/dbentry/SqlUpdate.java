package com.revature.project1.dbentry;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlUpdate extends SqlCrud{
    public SqlUpdate() {
        action = "update";
    }

    public void update(Object obj, Connection conn) throws IllegalAccessException, SQLException {
        setStatement(obj, condition);
        System.out.println(statement);

        Statement stmt = conn.createStatement();
        stmt.executeUpdate(statement);
        System.out.println("running sql command");
    }
}
