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
public class DataTypeToTextUtil {
    public static String parse(String type, String text){
        switch(type){
            case "NUMBER":
            case "INTEGER":
                text = toNumber(text);
                break;
            case "VARCHAR":
            case "VARCHAR2":
                text = toString(text);
                break;
            case "DATE":
                text = toDate(text);
                break;
        }
        return text;
    }
    public static String toString(String text){
        return "'" + text + "'";
    }
    
    public static String toNumber(String number){
        return number;
    }
    
    public static String toDate(String date){
        return "date(" + date + ")";
    }    
}
