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


    public void testNothing(String str){
        str = "test";
        str+= "and test";
    }

    public void insertNewObject(Object obj, Connection conn) throws IllegalAccessException {

        setStatement(obj);
        System.out.println(statement);


        PreparedStatement pstmt = null;
       Statement stmt = null;
        try {


            stmt = conn.createStatement();
           int rowsInserted = stmt.executeUpdate(statement);
            ResultSet rs = stmt.getGeneratedKeys();

 //           System.out.println(rowsInserted);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

            System.out.println("running sql command");
        //Run SQL command with connection to persist data
        //

    }

    //Wezley's method to print all members
    private void printMembers(Object[] members, String memberType) {
        if (members.length != 0) {
            System.out.println("\t" + memberType + " on class: ");
            for (Object o : members) {
                System.out.println("\t\t- " + o);
            }
        }
    }
}
