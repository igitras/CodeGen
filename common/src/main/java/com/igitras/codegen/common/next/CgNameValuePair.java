package com.igitras.codegen.common.next;

import com.igitras.codegen.common.next.annotations.NonNls;
import com.igitras.codegen.common.next.annotations.NotNull;
import com.igitras.codegen.common.next.annotations.Nullable;

/**
 * Represents a single element-value pair of an annotation parameter list.
 * <p>
 * Created by mason on 1/5/15.
 */
public interface CgNameValuePair extends CgElement {
    /**
     * Returns the identifier specifying the name of the element.
     *
     * @return the name identifier, or null if the annotation declaration is incomplete.
     */
    @Nullable
    CgIdentifier getNameIdentifier();

    /**
     * Returns the name of the element.
     *
     * @return the name, or null if the annotation declaration is incomplete.
     */
    @Nullable
    @NonNls
    String getName();

    @Nullable
    String getLiteralValue();

    /**
     * Returns the value for the element.
     *
     * @return the value for the element.
     */
    @Nullable
    CgAnnotationMemberValue getValue();

    @NotNull
    CgAnnotationMemberValue setValue(@NotNull CgAnnotationMemberValue newValue);
}
