package com.igitras.codegen.common.next;

/**
 * Represents a CG element (class, interface, method or constructor) which can own a type
 * parameter list.
 * <p>
 * Created by mason on 1/4/15.
 */
public interface CgTypeParameterListOwner extends CgMember {
    /**
     * Checks if the element has any type parameters.
     *
     * @return true if the element has type parameters, false otherwise
     */
    boolean hasTypeParameters();

    /**
     * Returns the type parameter list for the element.
     *
     * @return the type parameter list, or null if the element has no type parameters.
     */
    CgTypeParameterList getTypeParameterList();

    /**
     * Returns the array of type parameters for the element.
     *
     * @return the array of type parameters, or an empty array if the element has no type parameters.
     */
    CgTypeParameter[] getTypeParameters();
}
