package com.igitras.codegen.common.next;

import com.igitras.codegen.common.next.annotations.NotNull;

/**
 * Represents the list of name/value elements for an annotation.
 * <p>
 * Created by mason on 1/5/15.
 */
public interface CgAnnotationParameterList extends CgElement {
    /**
     * Returns the array of name/value elements specified on the annotation.
     *
     * @return the array of name/value pairs.
     */
    @NotNull
    CgNameValuePair[] getAttributes();
}
