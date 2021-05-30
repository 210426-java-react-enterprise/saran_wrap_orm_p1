package com.revature.project1.services;

import com.revature.project1.dbentry.SqlInsert;
import com.revature.project1.dbentry.SqlUpdate;
import com.revature.project1.models.AppUser;
import com.revature.project1.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

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

    //Updating one row/object in a database
    public String updateObject(Object obj, String key, String value) {
        SqlUpdate updateTest = new SqlUpdate();
        updateTest.setCondition(key, value);
        try {
            updateTest.update(obj, conn);
        } catch (IllegalAccessException | SQLException e) {
            e.printStackTrace();
        }
        return updateTest.getStatement();
    }
}
