package com.igitras.codegen.common.next;

/**
 * The common base interface for all elements of the Code Gen tree.
 * <p>
 * Created by mason on 1/4/15.
 */
public interface CgElement {

    /**
     * Returns the parent of the PSI element.
     *
     * @return the parent of the element, or null if the element has no parent.
     */
    //    @Contract(pure=true)
    CgElement getParent();
}
