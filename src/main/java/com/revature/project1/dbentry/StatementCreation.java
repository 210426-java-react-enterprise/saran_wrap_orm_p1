package com.revature.project1.dbentry;

import com.revature.project1.annotations.Column;
import com.revature.project1.annotations.Entity;
import com.revature.project1.annotations.Id;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class StatementCreation {
    protected Object obj;
    protected Entity entity;
    protected String tableName;
    protected String condition; // "uniqueFieldName = uniqueFieldValue"
    protected String statement;
    protected String action;
    protected HashMap<String, Object> data;
    protected Class<?> clazz;
    protected ArrayList<String> columnNames = new ArrayList<>();
    protected ArrayList<String> columnValues = new ArrayList<>();

    public void setStatement(Object obj) throws IllegalAccessException {
        this.obj = obj;
        setClazz();
        setTableName();
        setColumnNamesAndValues();

        // prepares the corresponding SQL statements
        switch (action) {
            case "insert into":
                String s1 = ""; // "column1, column2, column3, ..."
                String s2 = ""; // "value1, value2, value3, ..."
                for (int i = 0; i < columnValues.size(); i++) {
                    if(!columnNames.get(i).equals("user_id")){

                        s1 += columnNames.get(i);
                        s2 += "'" + columnValues.get(i) + "'";

                        if (i < columnValues.size()-1) {
                            s1 += ", ";
                            s2 += ", ";
                        }
                    }


                }
                //statement = "{action} {tableName} ({columnNames}) values ({columnValues})"; // insert format
                statement = String.format("%s %s (%s) values (%s)", action, tableName, s1, s2);
                break;
            case "select":
                //statement = "{action} {items} from {tableName} where ({condition})"; // select format
                if(condition == null){
                    statement = String.format("%s * from %s", action, tableName);
                } else {
                    statement = String.format("%s * from %s where (%s)", action, tableName, condition);
                }

                break;
            case "update":
                StringBuilder s = new StringBuilder(); // "column1=value1, column2=value2, ..."
                for (int i = 0; i < columnValues.size(); i++) {

                        s.append(columnNames.get(i))
                         .append("=")
                         .append("'")
                         .append(columnValues.get(i))
                         .append("'");
                    if (i < columnValues.size()-1) {
                        s.append(", ");
                    }
                }
                //statement = "{action} {tableName} set {columnName=columnValues} where ({condition})"; // update format
                statement = String.format("%s %s set %s where (%s)", action, tableName, s.toString(), condition);
                break;
            case "delete from":
                //statement = "{action} {tableName} where ({condition})"; // delete format
                statement = String.format("%s %s where (%s)", action, tableName, condition);
                break;
            default:
                System.out.println("Invalid action!");
        }
    }

    public void setStatement(Object obj, String condition) throws IllegalAccessException {
        this.condition = condition;
        setStatement(obj);
    }

    public void statementSetup(){
        this.statement = null;
        this.condition = null;
    }

    public boolean setTableName() {
        //Gets the class of the obj and uses the annotation to get the table name
        Class<?> clazz = obj.getClass();
        for (Annotation annotation : clazz.getAnnotations()) {
            if (!annotation.annotationType().getSimpleName().equals("Entity")) {
                return false;
            } else {
                entity = (Entity) annotation;
                tableName = entity.name();
            }
        }
        return true;
    }

    public void setClazz() {
        clazz = obj.getClass();
    }

    public void setColumnNamesAndValues() throws IllegalAccessException {

        //.filter(e -> e.getAnnotation(Column.class).annotationType().getSimpleName().equals("Column"))

        //Get all the names of fields tagged with Column
        List<String> names = Stream.of(clazz.getDeclaredFields())
                .filter(e -> e.getAnnotation(Column.class).annotationType().getSimpleName().equals("Column"))
                .map(e -> e.getAnnotation(Column.class).name())
                .collect(Collectors.toList());

        for (Object name : names) {
            columnNames.add(String.valueOf(name));
        }


        if (action.equals("select") || action.equals("delete from")) {
            return;
        }
        List<String> values = Stream.of(clazz.getDeclaredFields())
                .filter(e -> e.getAnnotation(Column.class).annotationType().getSimpleName().equals("Column"))
                .map(e -> getValueofField(e))
                .collect(Collectors.toList());


        for (Object value : values) {
            columnValues.add(String.valueOf(value));
        }
    }

    public String getValueofField(Field e){

        String temp = "";
        e.setAccessible(true);
        try {
         temp =  e.get(obj).toString();
        } catch (IllegalAccessException illegalAccessException) {
            illegalAccessException.printStackTrace();
        }
        e.setAccessible(false);

        return temp;
    }

    public String getStatement() {
        System.out.println("SQL -- " + action.toUpperCase() + ": " + statement);
        return statement;
    }

    public String getTableName() {
        return tableName;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
    public void setCondition(String key, String value) {
        condition = key + "=" + "'" + value + "'";
    }
    public void setCondition(String key, float value) {
        condition = key + "=" + value;
    }
    public void setCondition(String key, int value) {
        condition = key + "=" + value;
    }
}
