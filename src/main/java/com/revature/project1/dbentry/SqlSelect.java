package com.revature.project1.dbentry;

import com.revature.project1.annotations.Column;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SqlSelect extends StatementCreation {
    public SqlSelect() {
        action = "select";
    }

    public <T> ArrayList<T> select(Class<T> obj, String condition, Connection conn) {

        ArrayList<T> temp = new ArrayList<>();

        this.condition = condition;
        Statement stmt = null;

        try {
            setStatement(obj.newInstance());
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


        ArrayList<T> temp = new ArrayList<>();

        Statement stmt = null;

        try {
            setStatement(obj.newInstance());
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
