package com.igitras.codegen.common.next;

/**
 * Represents a list of generic type parameters for a class or method.
 * <p>
 * Created by mason on 1/4/15.
 */
public interface CgTypeParameterList extends CgElement {
    /**
     * Returns the array of type parameters contained in the list.
     *
     * @return the array of type parameters.
     */
    CgTypeParameter[] getTypeParameters();

    /**
     * Returns the index of the specified parameter in the list.
     *
     * @param typeParameter the parameter to find.
     * @return the index of the parameter.
     */
    int getTypeParameterIndex(CgTypeParameter typeParameter);
}
