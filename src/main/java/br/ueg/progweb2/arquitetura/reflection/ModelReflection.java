package br.ueg.progweb2.arquitetura.reflection;

import br.ueg.progweb2.arquitetura.annotations.MandatoryField;
import br.ueg.progweb2.arquitetura.model.GenericModel;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ModelReflection {

    static List<String> modelFieldAnnotation = List.of("Id", "Column", "JoinColumn");

    /**
     * Method responsible for return Entity fields from a given model each implements GenericModel
     * @param clazz
     * @return entityFields
     */
    public static List<Field> getEntityFields(GenericModel<?> clazz){

        List<Field> allFields = List.of(clazz.getClass().getDeclaredFields());
        List<Field> entityFields = new ArrayList<>(List.of());

        allFields.forEach(
                field -> Arrays.stream(field.getDeclaredAnnotations()).forEach(annotation -> {
                    if(isEntityAnnotation(annotation)){
                        entityFields.add(field);
                    }
            })
        );

        return entityFields;
    }

    /**
     * Method responsible for return the mandatory fields from a given model each implements GenericModel
     * @param clazz
     * @return mandatoryFields
     */
    public static List<Field> getMandatoryFields(GenericModel<?> clazz){
        List<Field> allFields = getEntityFields(clazz);
        List<Field> mandatoryFields = new ArrayList<>(List.of());
        allFields.forEach(field -> {
            if(field.isAnnotationPresent(MandatoryField.class)){
                mandatoryFields.add(field);
            }
        });
        return mandatoryFields;
    }


    /**
     *  Method responsible for return the value of a given field
     * @param clazz
     * @param returnType
     * @param field
     * @return result
     */
    @SuppressWarnings("unchecked")
    public static <T> T getFieldValue(GenericModel<?> clazz, T returnType, Field field){
        String fieldGetMethodName = "get" + StringReflection.uCFirst(field.getName());
        try {

            Method method = clazz.getClass().getMethod(fieldGetMethodName);
            return (T) method.invoke(clazz);

        } catch (Exception e) {
            throw new RuntimeException(
                    String.format("ERROR: While tying to call methods %s from %s: %s",
                    fieldGetMethodName, clazz.getClass().getSimpleName(), e.getMessage())
            );
        }
    }


    /**
     * Method responsible for  return if a given annotation represents an atribute af a field
     * @param annotation
     * @return isEntityAnnotation
     */
    public static boolean isEntityAnnotation(Annotation annotation){
        String name = annotation.annotationType().getSimpleName();
        return modelFieldAnnotation.contains(name);
    }


    /**
     * Method responsible for return  if a given field was filled
     * @param clazz
     * @param field
     * @return isFieldFilled
     */
    public static boolean isFieldFilled(GenericModel<?> clazz, Field field){
        Object result = getFieldValue(clazz, field.getType(), field);
        if(Objects.nonNull(result))
        {
            if(String.class.isAssignableFrom(field.getType())){
                String resultString = String.valueOf(result);
                return !resultString.isEmpty();
            }
            return true;
        }
        return false;
    }

    public static List<String> getInvalidMandatoryFields(GenericModel<?> clazz){
        List<Field> invalidMandatoryFields = new ArrayList<>(List.of());
        getMandatoryFields(clazz).forEach(field -> {
            if(!isFieldFilled(clazz, field)){
                invalidMandatoryFields.add(field);
            }
        });

        return StringReflection.fromFieldListToStringList(invalidMandatoryFields);
    }
}
