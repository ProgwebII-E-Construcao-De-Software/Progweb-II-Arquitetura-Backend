package br.ueg.progweb2.arquitetura.reflection;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class StringReflection {
    public static String uCFirst(String str){
        return str.substring(0,1).toUpperCase()+str.substring(1);
    }

    public static String fromFieldToString(Field field){
        return String.format(" Field: %s \nType: %s\n", field.getName(), field.getType().getSimpleName());
    }

    public static List<String> fromFieldListToStringList(List<Field> fieldList){
        List<String> list = new ArrayList<>();
        AtomicInteger i = new AtomicInteger();
        fieldList.forEach(field -> list.add(i.getAndIncrement() + fromFieldToString(field)));
        return list;
    }
}
