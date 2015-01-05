package com.igitras.codegen.common.next;

/**
 * Created by mason on 1/4/15.
 */
public interface CgMethod
        extends CgMember, CgNameIdentifierOwner, CgModifierListOwner, CgTypeParameterListOwner, CgDocCommentOwner {
    /**
     * Returns the return type of the method.
     *
     * @return the method return type, or null if the method is a constructor.
     */
    CgType getReturnType();

    /**
     * Returns the type element for the return type of the method.
     *
     * @return the type element for the return type, or null if the method is a constructor.
     */
    CgTypeElement getReturnTypeElement();

    /**
     * Returns the parameter list for the method.
     *
     * @return the parameter list instance.
     */
    CgParameterList getParameterList();

    /**
     * Returns the list of thrown exceptions for the method.
     *
     * @return the list of thrown exceptions instance.
     */
    CgReferenceList getThrowsList();

    /**
     * Returns the body of the method.
     *
     * @return the method body, or null if the method belongs to a compiled class.
     */
    CgCodeBlock getBody();


}
