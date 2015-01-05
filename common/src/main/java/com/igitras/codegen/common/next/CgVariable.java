package com.igitras.codegen.common.next;

import com.igitras.codegen.common.next.annotations.NonNls;
import com.igitras.codegen.common.next.annotations.NotNull;
import com.igitras.codegen.common.next.annotations.Nullable;
import com.igitras.codegen.common.next.exceptions.IncorrectOperationException;

/**
 * Represents a Java local variable, method parameter or field.
 * <p>
 * Created by mason on 1/4/15.
 */
public interface CgVariable extends CgModifierListOwner, CgNameIdentifierOwner {
    /**
     * Returns the type of the variable.
     *
     * @return the variable type.
     */
    @NotNull
    CgType getType();

    /**
     * Returns the type element declaring the type of the variable.
     *
     * @return the type element for the variable type.
     */
    @Nullable
    CgTypeElement getTypeElement();

    /**
     * Returns the initializer for the variable.
     *
     * @return the initializer expression, or null if it has no initializer.
     * @see {@link #hasInitializer()}
     */
    @Nullable
    CgExpression getInitializer();

    /**
     * <p>Checks if the variable has an initializer.</p>
     * <p>Please note that even when {@link #hasInitializer()} returns true, {@link #getInitializer()} still can return null,
     * e.g. for implicit initializer in case of enum constant declaration.</p>
     *
     * @return true if the variable has an initializer, false otherwise.
     */
    boolean hasInitializer();

    /**
     * Ensures that the variable declaration is not combined in the same statement with
     * other declarations. Also, if the variable is an array, ensures that the array
     * brackets are used in Java style (<code>int[] a</code>)
     * and not in C style (<code> int a[]</code>).
     *
     * @throws IncorrectOperationException if the modification fails for some reason.
     */
    void normalizeDeclaration()
            throws IncorrectOperationException; // Q: split into normalizeBrackets and splitting declarations?

    /**
     * Calculates and returns the constant value of the variable initializer.
     *
     * @return the calculated value, or null if the variable has no initializer or
     * the initializer does not evaluate to a constant.
     */
    @Nullable
    Object computeConstantValue();

    /**
     * Returns the identifier declaring the name of the variable.
     *
     * @return the variable name identifier.
     */
    @Override
    @Nullable
    CgIdentifier getNameIdentifier();

    @Override
    CgElement setName(@NonNls @NotNull String name) throws IncorrectOperationException;
}
