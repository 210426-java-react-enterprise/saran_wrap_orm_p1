package com.revature.project1.services;

import com.revature.project1.dbentry.SqlInsert;
import com.revature.project1.dbentry.SqlSelect;
import com.revature.project1.models.AppUser;
import com.revature.project1.util.ConnectionFactory;

import java.sql.Connection;
import java.util.ArrayList;

public class SaranServices {

    Connection conn;

    public SaranServices(){
        conn = ConnectionFactory.getInstance().getConnection();
    }

    //Inserting one obj into a database
    public String insertInDB(Object obj){
        SqlInsert insertTest = new SqlInsert();

        try {
            insertTest.insertNewObject(obj, conn);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        AppUser test = (AppUser) obj;

        return insertTest.getStatement();
    }

    public ArrayList<Object> selectAllDB(Object obj){
        SqlSelect selectTest = new SqlSelect();
        ArrayList<Object> allDBObjects = new ArrayList<>();
        try {
           allDBObjects = selectTest.selectAll(obj, conn);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return allDBObjects;
    }
}
