package com.guofei.wu.weekten.annotation.example;


import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 运行时注解处理器，构造表创建语句
 *
 * @author Mason
 * @author Mason
 * @version 2018/8/8
 * @since 2018/8/8
 */
public class TableCreator {
    public static String createTableSql(String className) throws ClassNotFoundException {
        Class<?> clazz = Class.forName(className);
        DBTable dbTable = clazz.getAnnotation(DBTable.class);
        if (dbTable == null) {
            System.out.println("No DBTable annotations in class  " + className);
            return null;
        }

        String tableName = dbTable.name();
        if (tableName == null || tableName.equals("")) {
            // if table name is null ,use the class name
            tableName = clazz.getName().toUpperCase();
        }

        // get the  field information
        List<String> columnDefines = new ArrayList<>();

//        iterator field
        for (Field field : clazz.getDeclaredFields()) {
            String columnName = null;

            //get field annotations

            Annotation[] annotations = field.getDeclaredAnnotations();

            // if annotations is null continue
            if (annotations == null) {
                continue;
            }

            if (annotations[0] instanceof SQLInteger) {
                SQLInteger sInt = (SQLInteger) annotations[0];
                String fieldName = sInt.name();
                if (fieldName == null || fieldName.equals("")) {
                    fieldName = field.getName().toUpperCase();
                }
                columnDefines.add(fieldName + " INT " + getConstraints(sInt.constraint()));
            }


            if (annotations[0] instanceof SQLString) {
                SQLString sString = (SQLString) annotations[0];

                String fieldName = sString.name();
                if (fieldName == null || fieldName.equals("")) {
                    fieldName = field.getName().toUpperCase();
                }
                columnDefines.add(fieldName + " VARCHAR(" + sString.value() + ")" + getConstraints(sString.constraint()));
            }
        }
        StringBuilder builder = new StringBuilder("CREATE TABLE");
        builder.append(tableName + "(");
        for (String s : columnDefines) {
            builder.append("\n");
            builder.append(s + ",");
        }
        return builder.substring(0, builder.lastIndexOf(",")) + "\n);";
    }

    private static String getConstraints(Constraints constraint) {
        String constraints = "";
        if (constraint.primaryKey()) {
            constraints += " PRIMARY KEY ";
        }
        if (!constraint.allowNull()) {
            constraints += " NOT NULL ";
        }
        if (constraint.unique()) {
            constraints += " UNIQUE ";
        }
        return constraints;

    }

    public static void main(String... args) throws ClassNotFoundException {
        String[] arg = {"com.guofei.wu.weekten.annotation.example.Member"};
        for (String className : arg) {
            System.out.println("Table Creation SQL for " +
                    className + " is :\n" + createTableSql(className));
        }
    }


}
