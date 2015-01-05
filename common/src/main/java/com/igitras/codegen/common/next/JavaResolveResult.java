package com.igitras.codegen.common.next;

import com.igitras.codegen.common.next.annotations.NotNull;

/**
 * JavaResolveResult holds additional information that is obtained
 * when Java references are being resolved.
 * <p>
 * Created by mason on 1/5/15.
 */
public interface JavaResolveResult extends ResolveResult {
    /**
     * Substitutor providing values of type parameters occurring in {@link #getElement()}.
     */
    @NotNull
    CgSubstitutor getSubstitutor();

    boolean isPackagePrefixPackageReference();

    /**
     * @return true if {@link #getElement()} is accessible from reference.
     */
    boolean isAccessible();

    boolean isStaticsScopeCorrect();

    /**
     * @return scope in the reference's file where the reference has been resolved,
     * {@code null} for qualified and local references.
     */
    CgElement getCurrentFileResolveScope();
}
