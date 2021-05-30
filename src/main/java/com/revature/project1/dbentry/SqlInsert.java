package com.revature.project1.dbentry;

import com.revature.project1.annotations.Column;
import com.revature.project1.annotations.Entity;
import com.revature.project1.annotations.Id;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SqlInsert extends SqlCrud{
    public SqlInsert() {
        action = "insert into";
    }

    public void insertNewObject(Object obj, Connection conn) throws IllegalAccessException {

        setStatement(obj);
        System.out.println(statement);


        PreparedStatement pstmt = null;
        Statement stmt = null;
        try {

//            pstmt = conn.prepareStatement(statement, new String[]{"user_id"});
            stmt = conn.createStatement();
           int rowsInserted = stmt.executeUpdate(statement);
            ResultSet rs = stmt.getGeneratedKeys();

 //           System.out.println(rowsInserted);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

            System.out.println("Ran SQL INSERT command");
        //Run SQL command with connection to persist data

    }
}
