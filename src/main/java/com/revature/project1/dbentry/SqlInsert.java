package com.revature.project1.dbentry;

import com.revature.project1.annotations.Entity;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SqlInsert {

    public void insertNewObject(Object obj) throws IllegalAccessException {

        //Gets the class of the obj and uses the annotation to get the table name
        Class<?> clazz = obj.getClass();
        Annotation[] classAnnotation = clazz.getAnnotations();
        String tableName = "";
        for(Annotation annotation : classAnnotation){
            if (annotation.annotationType().getSimpleName().equals("Entity")) {
                Entity test = (Entity) annotation;
                tableName = test.name();
                System.out.println("Table name " + tableName);
            }

        }


        //Get all the fields in the pojo
        Field[] declaredClassFields = clazz.getDeclaredFields();
        System.out.println("Field 1 name " + declaredClassFields[1].getName());
        //Use the field annotations and get all the column names
        ArrayList<Field> columnNames = new ArrayList<>();
        for(Field fields: declaredClassFields){
            Annotation[] fieldAnnotations = fields.getAnnotations();

            for (Annotation anno: fieldAnnotations){

                String annoName = anno.annotationType().getSimpleName();

                if(annoName.equals("Column")){
                    columnNames.add(fields);
                }
            }
        }

        //Mapped the column names and their values
        Map<String, Object> columnValues = new HashMap<String, Object>();

        for(Field names: columnNames){
            names.setAccessible(true);
            columnValues.put(names.getName(), names.get(obj));
           // System.out.println("Field Value: " + names.get(obj));
            //System.out.println("Map value: " + columnValues.get(names.getName()));
            //System.out.println("Field Name: " + names);
            //System.out.println("Field Value: " + columnValues.get(names.getName()));
            names.setAccessible(false);
        }

        //Create the start of sql string and adding table name
        String sqlInsert = "Insert into ";
        sqlInsert += tableName + " (";

        //Adds all the column ames but adds a , at the end will cause errors in the sql call
//        for(Field names: columnNames){
//            sqlInsert += names.getName()+",";
//        }

        //Adds the keys to the list but sorts them. Might cause some issues with aligning the values
        String temp = String.join(",", columnValues.keySet());
        System.out.println("Stream string: " + temp);
        sqlInsert += temp + ") values (";



        //sqlInset + tableName
        //SqlInsert + ( + all column names + )
        //SqlInsert values ( + all vales + )

        System.out.println("Sql String: " + sqlInsert);

        //Create key pairs of annotation and fields x
        //Use a stream to filter for table name
        //Use a stream to filter for all column names
    }

    //Wezeley's method to print all members
    private void printMembers(Object[] members, String memberType) {
        if (members.length != 0) {
            System.out.println("\t" + memberType + " on class: ");
            for (Object o : members) {
                System.out.println("\t\t- " + o);
            }
        }
    }
}
