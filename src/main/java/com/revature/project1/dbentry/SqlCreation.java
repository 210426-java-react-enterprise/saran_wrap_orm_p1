package com.revature.project1.dbentry;

import com.revature.project1.annotations.Column;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SqlCreation extends StatementCreation{

    public void insertNewObject(Object obj, Connection conn) {
        statementSetup();
        action = "insert into";
        Statement stmt = null;
        try {
            setStatement(obj);
            //System.out.println(statement);
            stmt = conn.createStatement();

            int rowsInserted = stmt.executeUpdate(statement);
            ResultSet rs = stmt.getGeneratedKeys();
            //System.out.println(rowsInserted);
        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
        }

        System.out.println("Ran SQL INSERT command");
        //Run SQL command with connection to persist data
    }

    public <T> ArrayList<T> select(Class<T> obj, String condition, Connection conn) {
        statementSetup();
        System.out.println("Select some");
        action = "select";
        ArrayList<T> temp = new ArrayList<>();
        this.statement = null;


        this.condition = condition;
        Statement stmt = null;

        try {
            setStatement(obj.newInstance());
            System.out.println(statement);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(statement);
            System.out.println(rs.toString());

            while (rs.next()){
                T t = obj.newInstance();
                loadResultSetIntoObject(rs, t);
                temp.add(t);

            }

        } catch (SQLException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        System.out.println("Ran SQL SELECT command. Returning results.");
        return temp;
    }

    public <T> ArrayList<T> selectAll(Class<T> obj, Connection conn){
        statementSetup();
        System.out.println("select All");
        action = "select";
        ArrayList<T> temp = new ArrayList<>();
        this.statement = null;

        Statement stmt = null;

        try {
            setStatement(obj.newInstance());
            System.out.println(statement);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(statement);
            System.out.println(rs.toString());

            while (rs.next()){
                T t = obj.newInstance();
                loadResultSetIntoObject(rs, t);
                temp.add(t);

            }

        } catch (SQLException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return temp;
    }

    public void update(Object obj, String condition, Connection conn)  {
        statementSetup();
        action = "update";
        this.condition = condition;
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

    //Requires the setCondition(...) method to be called first
    public void update(Object obj, Connection conn)  {
        action = "update";
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

    public <T> String delete(Class<T> obj, String condition, Connection conn){
        statementSetup();
        action = "delete from";
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

    //+------Helper functions------+
    // Used the below link as a reference on how to initialize a generic class from the DB
    // https://dzone.com/articles/method-to-get-jdbc-result-set-into-given-generic-c

    public <T> void loadResultSetIntoObject(ResultSet rs, Object obj) throws SQLException, IllegalAccessException {

        for (Field field: clazz.getDeclaredFields()){
            field.setAccessible(true);
            Object value = rs.getObject(field.getAnnotation(Column.class).name());
            Class<?> type = field.getType();
            if(isPrimitive(type)){
                Class<?> boxes = boxPrimitiveClass(type);
                value = boxes.cast(value);
            }
            field.set(obj, value);
        }

    }

    public boolean isPrimitive(Class<?> type){
        return (type == int.class || type == long.class || type == double.class || type == float.class
                || type == boolean.class || type == byte.class || type == char.class || type == short.class);
    }

    private Class<?> boxPrimitiveClass(Class<?> type){
        if (type == int.class) {
            return Integer.class;
        } else if (type == long.class) {
            return Long.class;
        } else if (type == double.class) {
            return Double.class;
        } else if (type == float.class) {
            return Float.class;
        } else if (type == boolean.class) {
            return Boolean.class;
        } else if (type == byte.class) {
            return Byte.class;
        } else if (type == char.class) {
            return Character.class;
        } else if (type == short.class) {
            return Short.class;
        } else {
            String string = "class '" + type.getName() + "' is not a primitive";
            throw new IllegalArgumentException(string);
        }
    }
}
