package com.sip.dmesmobile.utilities;

import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Proyecto: DMES - Versión: 1.0
 * @author (es): Gustavo Adolfo Chavarro Ortiz 
 * @Funcionalidad: Esta clase está encargada contener aquellas funciones que 
 * nos pueden ser utilies para diferentes clases o funcionalidades de la aplicación.
 * @Modificado por:  
 * @Funcionalidad agregada:
 */
public class Utilities
{

    //Arreglo con números y letras para generar la encriptación
    private static final char[] CONSTS_HEX =
    {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
    };

    /**
     * Método encargado de redondear una cantidad recibida a un número de dígitos indicados
     * @author: Gustavo Chavarro Ortiz
     * @param numero valor a redondear
     * @param digitos cantidad de digitos decimales a retornar
     * @return double valor a retornar
     */
    public static double Redondear(double numero, int digitos)
    {
        int cifras = (int) Math.pow(10, digitos);
        return Math.rint(numero * cifras) / cifras;
    }

    /**
     * Método encargado de de verificar si una cadena esta vacia
     * @author: Gustavo Chavarro Ortiz
     * @param value valor de la cadena a verificar
     * @return boolean indica si esta vacio o no
     */
    public static boolean isEmpty(String value)
    {
        boolean result = true;
        if (value != null)
        {
            value = value.replace(" ", "");
            if (value.length() > 0)
            {
                result = false;
            }
        }
        return result;
    }

    /**
     * Método encargado de indicar si una cadena es totalmente numerica
     * @author: Gustavo Chavarro Ortiz
     * @param value cadena recibida a verificar
     * @return boolean inidica si es numerico o no
     */
    public static boolean isNumeric(String value)
    {
        boolean result = false;
        String regex = "[0-9]*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        if (matcher.matches())
        {
            result = true;
        }
        return result;
    }

    /**
     * Método encargado de indicar si una cadena es alfanumerica
     * @author: Gustavo Chavarro Ortiz
     * @param value cadena recibida a verificar
     * @return boolean inidica si es alfanumerica o no
     */
    public static boolean isAlphaNumeric(String value)
    {
        boolean result = false;
        String regex = "[a-zA-Z0-9Ññ]*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        if (matcher.matches())
        {
            result = true;
        }
        return result;
    }

    /**
     * Método encargado de indicar si una cadena es alfabetica
     * @author: Gustavo Chavarro Ortiz
     * @param value cadena recibida a verificar
     * @return boolean inidica si es alfabetica o no
     */
    public static boolean isAlphabetical(String value)
    {
        boolean result = false;
        String regex = "[a-zA-ZÑñ\\t\\n\\x0b\\r\\f]*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        if (matcher.matches())
        {
            result = true;
        }
        return result;
    }

    /**
     * Método encargado de indicar si una cadena es un email
     * @author: Gustavo Chavarro Ortiz
     * @param value cadena recibida a verificar
     * @return boolean inidica si es un email o no
     */
    public static boolean isEmail(String value)
    {
        boolean result = false;
        String regex = "^[\\w-]+(\\.[\\w-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        if (matcher.matches())
        {
            result = true;
        }
        return result;
    }

    /**
     * Método encargado de encriptar una cadena recibida
     * @author: Gustavo Chavarro Ortiz
     * @param stringAEncriptar cadena recibida a encriptar
     * @return String valor de la candena encriptada
     */
    public static String encriptaEnMD5(String stringAEncriptar)
    {
        try
        {
            MessageDigest msgd = MessageDigest.getInstance("MD5");
            byte[] bytes = msgd.digest(stringAEncriptar.getBytes());
            StringBuilder strbCadenaMD5 = new StringBuilder(2 * bytes.length);
            for (int i = 0; i < bytes.length; i++)
            {
                int bajo = (int) (bytes[i] & 0x0f);
                int alto = (int) ((bytes[i] & 0xf0) >> 4);
                strbCadenaMD5.append(CONSTS_HEX[alto]);
                strbCadenaMD5.append(CONSTS_HEX[bajo]);
            }
            return strbCadenaMD5.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            return null;
        }
    }
    
    public static String fromArrayToJSON(Object list, String className, String nameArray)throws Exception
    {
        
        String result = "{\""+nameArray+"\":[";
        for(Object object: ((List<Object>) list))
        {
            result += fromObjectToJSON(object, className)+",";
        }
        result = result.substring(0, result.length()-1);
        result += "]}";
        return result;
    }
    
    public static String fromObjectToJSON(Object object, String className)throws Exception
    {
        String result = "{";
        if(object != null && className != null && className.length() > 0)
        {
                Class ObjectClass = Class.forName(className);
                Field[] fields = ObjectClass.getDeclaredFields();
                for(int i=0; i< fields.length; i++)
                {
                    if(isNative(fields[i].getGenericType().toString()))
                    {
                        if(!fields[i].getName().equals("serialVersionUID"))
                        {
                            result+= "\""+fields[i].getName()+"\":\""+fields[i].get(object)+"\"";
                            result+=","; 
                        }
                    }
                    else if(isForeing(fields[i].getGenericType().toString()))
                    {
                        if(!isList(fields[i].getGenericType().toString()))
                        {
                            result+= "\""+fields[i].getName()+"\":";
                            result+= fromObjectToJSON(fields[i].get(object), fields[i].getGenericType().toString().replace("class ", ""));
                            result+=",";
                        }
                    }
                    else if(isList(fields[i].getGenericType().toString()))
                    {
                        result+= "\""+fields[i].getName()+"\":";
                        result+= fromArrayToJSON(fields[i].get(object), ((List<Object>) fields[i].get(object)).get(0).getClass().getCanonicalName(), fields[i].getName());
                        result+=",";
                    }
                } 
                result = result.substring(0, result.length()-1);
            
        }
        result += "}";
        return result;
    }
    
    public static boolean isNative(String type)
    {
        boolean result = true;
        
        if(type.contains("co.sip.dmesmobile.entitys") || type.contains("java.util.List"))
        {
            result = false;
        }
        return result;
    }
    
    public static boolean isList(String type)
    {
        boolean result = false;
        if(type.contains("java.util.List"))
        {
            result = true;
        }
        return result;
    }
    
    public static boolean isForeing(String type)
    {
        boolean result = false;
        
        if(type.contains("co.sip.dmesmobile.entitys"))
        {
            result = true;
        }
        return result;
    }

    public static String messageJSON(String message)
    {
        return "{\"message\":\""+message+"\"}";
    }
    
    
    
    
}
