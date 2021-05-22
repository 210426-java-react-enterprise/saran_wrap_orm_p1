package com.revature.project1.dbentry;

import com.revature.project1.annotations.Entity;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class SqlCrud {
    Object obj;
    Entity entity;
    String tableName;
    String fields;
    String condition;
    String statement;
    HashMap<String, Object> data;
    Class<?> clazz;
    ArrayList<String> columnNames = new ArrayList<>();
    ArrayList<String> columnValues = new ArrayList<>();

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

    public void setFields() throws IllegalAccessException {
        //Get all the fields in the pojo
        for (Field fields : clazz.getDeclaredFields()) {

            for (Annotation anno: fields.getAnnotations()){
                if(anno.annotationType().getSimpleName().equals("Column")){
                    columnNames.add(fields.getName());
                    fields.setAccessible(true);
                    columnValues.add(fields.get(obj).toString());
                    fields.setAccessible(false);
                }
            }
        }
    }

    public String getTableName() {
        return tableName;
    }

    public Class<?> getClazz() {
        return clazz;
    }


}
