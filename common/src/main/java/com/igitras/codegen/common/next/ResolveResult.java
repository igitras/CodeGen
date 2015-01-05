package com.igitras.codegen.common.next;

import com.igitras.codegen.common.next.annotations.Nullable;

/**
 * Created by mason on 1/5/15.
 */
public interface ResolveResult {
    /**
     * Returns the result of the resolve.
     *
     * @return an element the reference is resolved to.
     */
    @Nullable
    CgElement getElement();

    /**
     * Checks if the reference was resolved to a valid element.
     *
     * @return true if the resolve encountered no problems
     */
    boolean isValidResult();
}
