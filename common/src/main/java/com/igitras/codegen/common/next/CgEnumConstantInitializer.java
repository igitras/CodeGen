package com.igitras.codegen.common.next;

import com.igitras.codegen.common.next.annotations.NotNull;

/**
 * Represents the class body attached to a constant in a Java enum type.
 * <p>
 * Created by mason on 1/5/15.
 */
public interface CgEnumConstantInitializer extends CgAnonymousClass {
    /**
     * Returns the enum constant to which the class body is attached.
     *
     * @return the enum constant instance.
     */
    @NotNull
    CgEnumConstant getEnumConstant();
}
