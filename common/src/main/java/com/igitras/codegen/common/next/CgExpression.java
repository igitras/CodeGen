package com.igitras.codegen.common.next;

import com.igitras.codegen.common.next.annotations.Nullable;

/**
 * Represents a Java expression.
 * <p>
 * Created by mason on 1/5/15.
 */
public interface CgExpression extends CgAnnotationMemberValue {
    /**
     * Returns the type of the expression.
     *
     * @return the expression type, or null if the type is not known.
     */
    @Nullable
    CgType getType();
}
