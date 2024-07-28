package com.fiap.ms_parquimetro_cadastro.utils;

import lombok.experimental.UtilityClass;

import java.lang.reflect.Field;
@UtilityClass
public class ReflectionMethod {
    public static <T> void updateEntity(T source, T target) {
        try {
        Class<?> clazz = source.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            Object value = field.get(source);
            if (!field.getName().equals("clienteId")) {
                if (value != null) {
                    Field targetField;

                    targetField = target.getClass().getDeclaredField(field.getName());
                    targetField.setAccessible(true);
                    if (targetField.getType().isAssignableFrom(value.getClass())) {
                        targetField.set(target, value);
                    } else {
                        throw new RuntimeException("Error to convert field " + field.getName() + " of class " + target.getClass().getName());
                    }
                }
            }
        }
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
