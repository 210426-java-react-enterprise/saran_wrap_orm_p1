package com.revature.project1.dbentry;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.stream.Stream;

public class SqlInsert {

    public void insertNewObject(Object obj){

        //prints the class of the obj
        Class<?> clazz = obj.getClass();

        //prints the fields of the class
        Field[] declaredClassFields = clazz.getDeclaredFields();
        printMembers(declaredClassFields, "Declared Fields");

        ArrayList<Field> columnNames = new ArrayList<>();

        //prints the annotations of each field
        for(Field fields: declaredClassFields){
            Annotation[] fieldAnnotations = fields.getAnnotations();
            printMembers(fieldAnnotations, "Annotations");
            for (Annotation anno: fieldAnnotations){

                String annoName = anno.annotationType().getSimpleName();

                if(annoName.equals("Column")){
                    columnNames.add(fields);
                }
            }
        }

        for(Field names: columnNames){
            System.out.println("ColumnName: " + names.getName());
        }
        //Create key pairs of annotation and fields
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
