package com.igitras.codegen.common.next;

import com.igitras.codegen.common.next.annotations.NotNull;
import com.igitras.codegen.common.next.annotations.Nullable;
import com.igitras.codegen.common.next.exceptions.IncorrectOperationException;

/**
 * Created by mason on 1/4/15.
 */
public interface CgField extends CgMember, CgVariable, CgDocCommentOwner {

    /**
     * Adds initializer to the field declaration or, if <code>initializer</code> parameter is null,
     * removes the initializer from the field declaration.
     *
     * @param initializer the initializer to add.
     * @throws IncorrectOperationException if the modifications fails for some reason.
     * @since 5.0.2
     */
    void setInitializer(@Nullable CgExpression initializer) throws IncorrectOperationException;

    @Override
    @NotNull
    CgIdentifier getNameIdentifier();
}
