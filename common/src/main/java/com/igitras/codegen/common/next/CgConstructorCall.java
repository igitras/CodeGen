package com.igitras.codegen.common.next;

import com.igitras.codegen.common.next.annotations.Nullable;

/**
 * Represents a Java constructor call or enum constant.
 * <p>
 * Created by mason on 1/5/15.
 */
public interface CgConstructorCall extends CgCall {
    /**
     * Resolves the reference to the called constructor and returns the constructor.
     * Equivalent to {@link com.igitras.codegen.common.next.CgCall#resolveMethod()}.
     *
     * @return the called constructor, or null if the resolve failed.
     */
    @Nullable
    CgMethod resolveConstructor();
}
