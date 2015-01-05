package com.igitras.codegen.common.next;

import com.igitras.codegen.common.next.annotations.NonNls;
import com.igitras.codegen.common.next.annotations.NotNull;
import com.igitras.codegen.common.next.annotations.Nullable;

/**
 * Represents a Java annotation.
 * <p>
 * Created by mason on 1/5/15.
 */
public interface CgAnnotation extends CgAnnotationMemberValue, CgMetaOwner {

    /**
     * Kinds of element to which an annotation type is applicable (see {@link java.lang.annotation.ElementType}).
     */
    enum TargetType {
        // see java.lang.annotation.ElementType
        TYPE,
        FIELD,
        METHOD,
        PARAMETER,
        CONSTRUCTOR,
        LOCAL_VARIABLE,
        ANNOTATION_TYPE,
        PACKAGE,
        TYPE_USE,
        TYPE_PARAMETER,
        // auxiliary value, used when it's impossible to determine annotation's targets
        UNKNOWN;

        public static final TargetType[] EMPTY_ARRAY = {};
    }

    /**
     * Returns the list of parameters for the annotation.
     *
     * @return the parameter list instance.
     */
    @NotNull
    CgAnnotationParameterList getParameterList();

    /**
     * Returns the fully qualified name of the annotation class.
     *
     * @return the class name, or null if the annotation is unresolved.
     */
    @Nullable
    @NonNls
    String getQualifiedName();

    /**
     * Returns the element representing the name of the annotation.
     *
     * @return the annotation name element.
     */
    @Nullable
    CgNamedElement getNameReferenceElement();


    /**
     * Set annotation attribute value. Adds new name-value pair or uses an existing one, expands unnamed 'value' attribute name if needed.
     *
     * @param attributeName attribute name
     * @param value         new value template element
     * @return new declared attribute value
     */
    <T extends CgAnnotationMemberValue> T setDeclaredAttributeValue(
            @Nullable @NonNls String attributeName, @Nullable T value);

    /**
     * Returns an owner of the annotation - usually a parent, but for type annotations the owner might be a type element.
     *
     * @return annotation owner
     */
    @Nullable
    CgAnnotationOwner getOwner();
}
