package com.igitras.codegen.common.next;

import com.igitras.codegen.common.next.annotations.NonNls;
import com.igitras.codegen.common.next.annotations.NotNull;
import com.igitras.codegen.common.next.annotations.Nullable;
import com.igitras.codegen.common.next.exceptions.IncorrectOperationException;

/**
 * A PSI element which has a name and can be renamed (for example, a class or a method).
 * <p>
 * Created by mason on 1/4/15.
 */
public interface CgNamedElement extends CgElement {
    /**
     * Returns the name of the element.
     *
     * @return the element name.
     */
    @Nullable
    @NonNls
    String getName();

    /**
     * Renames the element.
     *
     * @param name the new element name.
     * @return the element corresponding to this element after the rename (either <code>this</code>
     * or a different element if the rename caused the element to be replaced).
     * @throws IncorrectOperationException if the modification is not supported or not possible for some reason.
     */
    CgElement setName(@NonNls @NotNull String name) throws IncorrectOperationException;
}
