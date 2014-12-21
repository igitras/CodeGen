package com.igitras.codegen.common.java.utils;

import com.igitras.codegen.common.Directory;
import com.igitras.codegen.common.java.element.JavaDirectory;
import com.igitras.codegen.common.java.element.JavaFile;
import com.igitras.codegen.common.java.element.file.JavaClassFile;
import com.igitras.codegen.common.java.element.file.JavaEnumFile;
import com.igitras.codegen.common.java.element.file.JavaInterfaceFile;
import com.igitras.codegen.common.java.element.file.part.JavaAnnotationPart;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mason on 12/21/14.
 */
public abstract class Utils {
    public static JavaFile reflectResolveFile(String fileName, JavaDirectory directory) {
        try {
            Class<?> clazz = Class.forName(fileName);
            if (clazz.isEnum()) {
                return reflectResolveEnum(clazz, directory);
            } else if (clazz.isAnnotation()) {
                return reflectResolveAnnotation(clazz, directory);
            } else if (clazz.isInterface()) {
                return reflectResolveInterface(clazz, directory);
            } else {
                return reflectResolveClass(clazz, directory);
            }
        } catch (ClassNotFoundException e) {

        }
        return null;

    }

    public static JavaClassFile reflectResolveClass(Class<?> clazz, JavaDirectory directory) {

        return null;
    }

    public static JavaFile reflectResolveAnnotation(Class<?> clazz, JavaDirectory directory) {
        throw new UnsupportedOperationException("Not Implemented yet.");
    }

    public static JavaInterfaceFile reflectResolveInterface(Class<?> clazz, JavaDirectory directory) {
        JavaInterfaceFile interfaceFile = new JavaInterfaceFile(clazz.getName(), directory);
        Annotation[] declaredAnnotations = clazz.getDeclaredAnnotations();

        Method[] declaredMethods = clazz.getDeclaredMethods();
        Class<?>[] interfaces = clazz.getInterfaces();
        return null;
    }

    public static JavaEnumFile reflectResolveEnum(Class<?> clazz, Directory directory) {
        Object[] enumConstants = clazz.getEnumConstants();
        return null;
    }

    protected static List<JavaAnnotationPart> toAnnotationParts(Annotation[] annotations){
        List<JavaAnnotationPart> annotationParts = new ArrayList<JavaAnnotationPart>(annotations.length);
        for (Annotation annotation : annotations) {
            JavaAnnotationPart annotationPart = new JavaAnnotationPart(annotation.getClass().getName());
            //TODO:
            annotation.annotationType().getClass().getName();
            annotationParts.add(annotationPart);
        }
        return annotationParts;
    }
}
