package com.igitras.codegen.common.next;

import com.igitras.codegen.common.next.annotations.NotNull;
import com.igitras.codegen.common.next.annotations.Nullable;

/**
 * Represents a Java anonymous class.
 * <p>
 * Created by mason on 1/5/15.
 */
public interface CgAnonymousClass extends CgClass {
    /**
     * Returns the element specifying the base class for the anonymous class.
     *
     * @return the element for the base class.
     */
    @NotNull
    CgClass getBaseClass();

    /**
     * Returns the type for the base class of the anonymous class.
     *
     * @return the type for the base class.
     */
    @NotNull
    CgClassType getBaseClassType();

    /**
     * Returns the list of arguments passed to the base class constructor.
     *
     * @return the argument list, or null if no argument list was specified.
     */
    @Nullable
    CgExpressionList getArgumentList();

    boolean isInQualifiedNew();
}
