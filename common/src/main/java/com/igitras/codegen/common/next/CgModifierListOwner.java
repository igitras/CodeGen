package com.igitras.codegen.common.next;

import com.igitras.codegen.common.next.annotations.NonNls;
import com.igitras.codegen.common.next.annotations.NotNull;

/**
 * Represents a PSI element which has a list of modifiers (public/private/protected/etc.)
 * and annotations.
 * <p>
 * Created by mason on 1/4/15.
 */
public interface CgModifierListOwner extends CgElement {

    /**
     * Returns the list of modifiers for the element.
     *
     * @return the list of modifiers, or null if the element (for example, an anonymous
     * inner class) does not have the list of modifiers.
     */
    CgModifierList getModifierList();

    /**
     * Checks if the element has the specified modifier. Possible modifiers are defined
     * as constants in the {@link CgModifier} class.
     *
     * @param name the name of the modifier to check.
     * @return true if the element has the modifier, false otherwise
     */
    boolean hasModifierProperty(@CgModifier.ModifierConstant @NonNls @NotNull String name);
}
