package com.igitras.codegen.common.next;

/**
 * Represents a PSI element which can have an attached JavaDoc comment.
 * <p>
 * Created by mason on 1/4/15.
 */
public interface CgDocCommentOwner extends CgMember {

    /**
     * Returns the JavaDoc comment for the element.
     *
     * @return the JavaDoc comment instance, or null if the element has no JavaDoc comment.
     */
    CgDocComment getDocComment();
}
