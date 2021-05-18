package com.revature.project1.dbentry;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class SqlInsert {

    public void insertNewObject(Object obj) throws IllegalAccessException {

        //prints the class of the obj
        Class<?> clazz = obj.getClass();

        //prints the fields of the class
        Field[] declaredClassFields = clazz.getDeclaredFields();


        ArrayList<Field> columnNames = new ArrayList<>();

        //prints the annotations of each field
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
            System.out.println("Field Value: " + names.get(obj));
            System.out.println("Map value: " + columnValues.get(names.getName()));
            names.setAccessible(false);
        }

        //Create the sql string
        String sqlInsert = "Insert into ";
        //sqlInset + tableName
        //SqlInsert + ( + all column names + )
        //SqlInsert values ( + all vales + )



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
