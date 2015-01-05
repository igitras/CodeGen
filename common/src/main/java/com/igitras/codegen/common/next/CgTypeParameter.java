package com.igitras.codegen.common.next;

/**
 * Represents the type parameter of a generic class, interface, method or constructor.
 * <p>
 * Created by mason on 1/4/15.
 */
public interface CgTypeParameter extends CgClass, CgAnnotationOwner {

    /**
     * Returns the element which is parameterized by the type parameter.
     *
     * @return the type parameter owner instance.
     */
    CgTypeParameterListOwner getOwner();
}
