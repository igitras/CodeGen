package com.igitras.codegen.common.next;

import com.igitras.codegen.common.next.annotations.NotNull;
import com.igitras.codegen.common.next.annotations.Nullable;

/**
 * Represents the call of a Java method or constructor or a Java enum constant..
 * <p>
 * Created by mason on 1/5/15.
 */
public interface CgCall extends CgElement {

    /**
     * Returns the list of arguments passed to the called method.
     *
     * @return the argument list, or null if the call is incomplete.
     */
    @Nullable
    CgExpressionList getArgumentList();

    /**
     * Resolves the reference to the called method and returns the method.
     *
     * @return the called method, or null if the resolve failed.
     */
    @Nullable
    CgMethod resolveMethod();

    /**
     * Resolves the reference to the called method and returns the resolve result
     * containing the method and the substitutor for generic type parameters.
     *
     * @return the resolve result, or {@link JavaResolveResult#EMPTY} if unresolved
     */
    @NotNull
    JavaResolveResult resolveMethodGenerics();
}
