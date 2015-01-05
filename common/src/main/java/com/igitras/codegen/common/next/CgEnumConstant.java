package com.igitras.codegen.common.next;

import com.igitras.codegen.common.next.annotations.NotNull;
import com.igitras.codegen.common.next.annotations.Nullable;

/**
 * Represents a constant in a Java enum type.
 * <p>
 * Created by mason on 1/5/15.
 */
public interface CgEnumConstant extends CgField, CgConstructorCall {

    /**
     * Returns the list of arguments passed to the constructor of the enum type to create the
     * instance of the constant.
     *
     * @return the list of arguments, or null
     */
    @Override
    @Nullable
    CgExpressionList getArgumentList();

    /**
     * Returns the class body attached to the enum constant declaration.
     *
     * @return the enum constant class body, or null if
     * the enum constant does not have one.
     */
    @Nullable
    CgEnumConstantInitializer getInitializingClass();

    @NotNull
    CgEnumConstantInitializer getOrCreateInitializingClass();
}
