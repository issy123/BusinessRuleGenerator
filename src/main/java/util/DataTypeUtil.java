/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author ismail
 */
public class DataTypeUtil {
    public static String toText(String type, String text){
        switch(type){
            case "NUMBER":
            case "INTEGER":
                text = text;
                break;
            case "VARCHAR":
            case "VARCHAR2":
                text = "'" + text + "'";
                break;
            case "DATE":
                text = "date(" + text + ")";
                break;
        }
        return text;
    }
    
    public static String toType(String type){
        switch(type){
            case "NUMBER":
            case "INTEGER":
                type = type;
                break;
            case "VARCHAR":
                type = type + "(255)";
                break;
            case "VARCHAR2":
                type = type + "(4000)";
                break;
            case "DATE":
                type = type;
                break;
        }
        return type;
    }
    
}
