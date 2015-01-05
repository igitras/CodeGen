package com.igitras.codegen.common.next;

import com.igitras.codegen.common.next.annotations.Nullable;

/**
 * Represents a method which is declared on an annotation interface and
 * possibly specifies the default value of the annotation element.
 * <p>
 * Created by mason on 1/5/15.
 */
public interface CgAnnotationMethod extends CgMethod {
    /**
     * Returns the default value of the annotation element defined by the method.
     *
     * @return the default value of the element, or null if no default value is specified.
     */
    @Nullable
    CgAnnotationMemberValue getDefaultValue();
}
