package com.igitras.codegen.common.next;

/**
 * Implementers of this interface may provide their own way of finding an associated doc comment owner element
 * which may be essential for languages other than Java.
 * <p>
 * Created by mason on 1/4/15.
 */
public interface CgDocCommentBase extends CgComment {

    /**
     * @return The element which this doc comment refers to or null if there is no such element.
     */
    CgElement getOwner();
}
