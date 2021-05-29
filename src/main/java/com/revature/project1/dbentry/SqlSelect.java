package com.revature.project1.dbentry;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SqlSelect extends SqlCrud {
    public SqlSelect() {
        action = "select";
    }

    public ArrayList<Object> selectAll(Object obj, Connection conn) throws IllegalAccessException {

        setStatement(obj);

        Statement stmt = null;

        try {

            stmt = conn.createStatement();
           ResultSet rs = stmt.executeQuery(statement);
            System.out.println(rs.toString());

            List<List<String>> dbObjects = new ArrayList<>();
            while (rs.next()){
                List<String> columnValues = new ArrayList<>();

                for (int i = 1; i < rs.getMetaData().getColumnCount(); i++){
                    columnValues.add(rs.getString(i));
                }

                dbObjects.add(columnValues);
            }

            System.out.println(dbObjects);

        }catch (SQLException throwables){
            throwables.printStackTrace();
        }

        System.out.println("running select command");
        return null;
    }


}
