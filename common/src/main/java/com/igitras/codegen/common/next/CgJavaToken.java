package com.igitras.codegen.common.next;

import com.igitras.codegen.common.next.tree.IElementType;

/**
 * Represents a single token in a Java file (the lowest-level element in the Java PSI tree).
 * <p>
 * Created by mason on 1/5/15.
 */
public interface CgJavaToken extends CgElement {
    /**
     * Returns the type of the token.
     *
     * @return the token type.
     */
    IElementType getTokenType();
}
