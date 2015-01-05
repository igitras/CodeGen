package com.igitras.codegen.common.next;

import com.igitras.codegen.common.next.annotations.NonNls;
import com.igitras.codegen.common.next.annotations.Nullable;

/**
 * <tt>CgQualifiedNamedElement</tt> interface marks psi elements that can have
 * fully qualified name and defines parent-child like relationship between such
 * elements.
 * <p>
 * Implementations of <tt>CgClass</tt>, <tt>CgPackage</tt> and <tt>CgAnnotation</tt>
 * for Java all implement <tt>CgQualifiedNamedElement</tt> interface
 * <p>
 * Created by mason on 1/5/15.
 */
public interface CgQualifiedNamedElement {
    /**
     * Returns the fully qualified name of the element.
     *
     * @return the qualified name of the element, or null
     */
    @Nullable
    @NonNls
    String getQualifiedName();

    /**
     * Returns the name of the element.
     *
     * @return the element name
     */
    @Nullable
    @NonNls
    String getName();

}
