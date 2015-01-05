package com.igitras.codegen.common.java.utils;

import com.igitras.codegen.common.Directory;
import com.igitras.codegen.common.java.element.JavaDirectory;
import com.igitras.codegen.common.java.element.JavaFile;
import com.igitras.codegen.common.java.element.enums.IsAbstract;
import com.igitras.codegen.common.java.element.file.JavaClassFile;
import com.igitras.codegen.common.java.element.file.JavaEnumFile;
import com.igitras.codegen.common.java.element.file.JavaInterfaceFile;
import com.igitras.codegen.common.java.element.file.part.JavaAbstractMethodPart;
import com.igitras.codegen.common.java.element.file.part.JavaAnnotationPart;
import com.igitras.codegen.common.java.element.file.part.JavaCommaSplitEntry;
import com.igitras.codegen.common.java.element.file.part.JavaFieldPart;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
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
        JavaClassFile classFile = new JavaClassFile(clazz.getName(), directory);

        Annotation[] declaredAnnotations = clazz.getDeclaredAnnotations();
        List<JavaAnnotationPart> annotationParts = toAnnotationParts(declaredAnnotations);
        for (JavaAnnotationPart annotationPart : annotationParts) {
            classFile.addAnnotation(annotationPart);
        }

        Field[] declaredFields = clazz.getDeclaredFields();
        List<JavaFieldPart> javaFieldParts = toFieldsPart(declaredFields);
        for (JavaFieldPart javaFieldPart : javaFieldParts) {
            classFile.addField(javaFieldPart);
        }

        Method[] declaredMethods = clazz.getDeclaredMethods();
        List<JavaAbstractMethodPart> abstractMethodParts = toAbstractMethodParts(declaredMethods);
        for (JavaAbstractMethodPart abstractMethodPart : abstractMethodParts) {
            classFile.addAbstractMethod(abstractMethodPart);
        }

        return null;
    }

    public static JavaFile reflectResolveAnnotation(Class<?> clazz, JavaDirectory directory) {
        throw new UnsupportedOperationException("Not Implemented yet.");
    }

    public static JavaInterfaceFile reflectResolveInterface(Class<?> clazz, JavaDirectory directory) {
        JavaInterfaceFile interfaceFile = new JavaInterfaceFile(clazz.getName(), directory);
        Annotation[] declaredAnnotations = clazz.getDeclaredAnnotations();
        List<JavaAnnotationPart> annotationParts = toAnnotationParts(declaredAnnotations);
        for (JavaAnnotationPart annotationPart : annotationParts) {
            interfaceFile.addAnnotation(annotationPart);
        }

        Method[] declaredMethods = clazz.getDeclaredMethods();
        List<JavaAbstractMethodPart> abstractMethodParts = toAbstractMethodParts(declaredMethods);
        for (JavaAbstractMethodPart abstractMethodPart : abstractMethodParts) {
            interfaceFile.addAbstractMethod(abstractMethodPart);
        }

        Field[] declaredFields = clazz.getDeclaredFields();
        List<JavaFieldPart> javaFieldParts = toFieldsPart(declaredFields);
        for (JavaFieldPart javaFieldPart : javaFieldParts) {
            interfaceFile.addField(javaFieldPart);
        }

        Class<?>[] interfaces = clazz.getInterfaces();
        for (Class<?> inter : interfaces) {
            interfaceFile.getExtendsPart().getCommaSplitList().addEntries(new JavaCommaSplitEntry(inter.getName()));
            //TODO:

        }
        return interfaceFile;
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


    private static List<JavaAbstractMethodPart> toAbstractMethodParts(Method[] methods) {
        List<JavaAbstractMethodPart> abstractMethodParts = new ArrayList<JavaAbstractMethodPart>();
        for (Method method : methods) {
            JavaAbstractMethodPart abstractMethodPart =
                    new JavaAbstractMethodPart(IsAbstract.DEFAULT, method.getReturnType().getName(), method.getName());
            //TODO:
            abstractMethodParts.add(abstractMethodPart);
        }
        return abstractMethodParts;
    }

    private static List<JavaFieldPart> toFieldsPart(Field[] declaredFields) {
        List<JavaFieldPart> fieldParts = new ArrayList<JavaFieldPart>();
        for (Field field : declaredFields) {
            JavaFieldPart fieldPart = null;
            //TODO:

            fieldParts.add(fieldPart);
        }
        return fieldParts;
    }
}
