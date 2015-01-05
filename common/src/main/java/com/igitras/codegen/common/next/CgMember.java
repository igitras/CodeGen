package com.igitras.codegen.common.next;

/**
 * Represents a member of a Java class (for example, a field or a method).
 * <p>
 * Created by mason on 1/4/15.
 */
public interface CgMember extends CgModifierListOwner {

    /**
     * Returns the class containing the member.
     *
     * @return the containing class.
     */
    CgClass getContainingClass();
}
