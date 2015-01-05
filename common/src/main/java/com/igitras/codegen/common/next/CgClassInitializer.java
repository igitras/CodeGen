package com.igitras.codegen.common.next;

/**
 * Created by mason on 1/4/15.
 */
public interface CgClassInitializer extends CgMember {
    /**
     * Returns the contents of the class initializer block.
     *
     * @return the code block representing the contents of the class initializer block.
     */
    CgCodeBlock getBody();
}
