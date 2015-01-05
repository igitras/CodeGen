package com.igitras.codegen.common.next;

import com.igitras.codegen.common.next.annotations.NonNls;
import com.igitras.codegen.common.next.annotations.NotNull;
import com.igitras.codegen.common.next.annotations.Nullable;
import com.igitras.codegen.common.next.exceptions.IncorrectOperationException;

/**
 * Represents a Java class or interface.
 *
 * Created by mason on 1/4/15.
 */
public interface CgClass
        extends CgModifierListOwner, CgNameIdentifierOwner, CgDocCommentOwner, CgTypeParameterListOwner {

    /**
     * Returns the fully qualified name of the class.
     *
     * @return the qualified name of the class, or null for anonymous and local classes, and for type parameters
     */
    @Nullable
    @NonNls
    String getQualifiedName();

    /**
     * Checks if the class is an interface.
     *
     * @return true if the class is an interface, false otherwise.
     */
    boolean isInterface();

    /**
     * Checks if the class is an annotation type.
     *
     * @return true if the class is an annotation type, false otherwise
     */
    boolean isAnnotationType();

    /**
     * Checks if the class is an enumeration.
     *
     * @return true if the class is an enumeration, false otherwise.
     */
    boolean isEnum();

    /**
     * Returns the list of classes that this class or interface extends.
     *
     * @return the extends list, or null for anonymous classes, enums and annotation types
     */
    @Nullable
    CgReferenceList getExtendsList();

    /**
     * Returns the list of interfaces that this class implements.
     *
     * @return the implements list, or null for anonymous classes
     */
    @Nullable
    CgReferenceList getImplementsList();

    /**
     * Returns the list of class types for the classes that this class or interface extends.
     *
     * @return the list of extended class types, or an empty list for anonymous classes,
     * enums and annotation types
     */
    @NotNull
    CgClassType[] getExtendsListTypes();

    /**
     * Returns the list of class types for the interfaces that this class implements.
     *
     * @return the list of extended class types, or an empty list for anonymous classes,
     * enums and annotation types
     */
    @NotNull
    CgClassType[] getImplementsListTypes();

    /**
     * Returns the base class of this class.
     *
     * @return the base class. May return null when jdk is not configured, so no java.lang.Object is found,
     * or for java.lang.Object itself
     */
    @Nullable
    CgClass getSuperClass();

    /**
     * Returns the list of interfaces implemented by the class, or extended by the interface.
     *
     * @return the list of interfaces.
     */
    CgClass[] getInterfaces();

    /**
     * Returns the list of classes and interfaces extended or implemented by the class.
     *
     * @return the list of classes or interfaces. May return zero elements when jdk is
     * not configured, so no java.lang.Object is found
     */
    @NotNull
    CgClass[] getSupers();

    /**
     * Returns the list of class types for the classes and interfaces extended or
     * implemented by the class.
     *
     * @return the list of class types for the classes or interfaces.
     * For the class with no explicit extends list, the returned list always contains at least one element for the java.lang.Object type.
     * If psiClass is java.lang.Object, returned list is empty.
     */
    @NotNull
    CgClassType[] getSuperTypes();

    /**
     * Returns the list of fields in the class.
     *
     * @return the list of fields.
     */
    CgField[] getFields();

    /**
     * Returns the list of methods in the class.
     *
     * @return the list of methods.
     */
    CgMethod[] getMethods();

    /**
     * Returns the list of constructors for the class.
     *
     * @return the list of constructors,
     */
    CgMethod[] getConstructors();

    /**
     * Returns the list of inner classes for the class.
     *
     * @return the list of inner classes.
     */
    CgClass[] getInnerClasses();

    /**
     * Returns the list of class initializers for the class.
     *
     * @return the list of class initializers.
     */
    CgClassInitializer[] getInitializers();

    /**
     * Returns the list of fields in the class and all its superclasses.
     *
     * @return the list of fields.
     */
    CgField[] getAllFields();

    /**
     * Returns the list of methods in the class and all its superclasses.
     *
     * @return the list of methods.
     */
    CgMethod[] getAllMethods();

    /**
     * Returns the list of inner classes for the class and all its superclasses..
     *
     * @return the list of inner classes.
     */
    CgClass[] getAllInnerClasses();

    /**
     * For an inner class, returns its containing class.
     *
     * @return the containing class, or null if the class is not an inner class.
     */
    @Override
    @Nullable
    CgClass getContainingClass();

    @Override
    CgElement setName(@NonNls @NotNull String name) throws IncorrectOperationException;
}
