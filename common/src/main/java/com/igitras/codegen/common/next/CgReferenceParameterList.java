package com.igitras.codegen.common.next;

import com.igitras.codegen.common.next.annotations.NotNull;

/**
 * Represents the list of type arguments specified on a Java reference.
 * <p>
 * Created by mason on 1/5/15.
 */
public interface CgReferenceParameterList extends CgElement {
    /**
     * Returns the array of type elements used as type arguments.
     *
     * @return the array of type elements.
     */
    @NotNull
    CgTypeElement[] getTypeParameterElements();

    /**
     * Returns the array of types corresponding to type elements used as type arguments.
     *
     * @return the array of types.
     */
    @NotNull
    CgType[] getTypeArguments();
}
