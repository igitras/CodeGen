package com.igitras.codegen.common.next;

import com.igitras.codegen.common.next.annotations.NotNull;

/**
 * Represents a list of expressions separated by commas.
 * <p>
 * Created by mason on 1/5/15.
 */
public interface CgExpressionList extends CgElement {
    /**
     * Returns the expressions contained in the list.
     *
     * @return the array of expressions contained in the list.
     */
    @NotNull
    CgExpression[] getExpressions();

    @NotNull
    CgType[] getExpressionTypes();


}
