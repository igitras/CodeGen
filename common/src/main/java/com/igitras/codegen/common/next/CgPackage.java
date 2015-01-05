package com.igitras.codegen.common.next;

import com.igitras.codegen.common.next.annotations.NotNull;
import com.igitras.codegen.common.next.annotations.Nullable;

/**
 * Represents a Java package.
 * <p>
 * Created by mason on 1/5/15.
 */
public interface CgPackage
        extends CgCheckedRenameElement, CgModifierListOwner, CgDirectoryContainer, CgQualifiedNamedElement {
    /**
     * Returns the parent of the package.
     *
     * @return the parent package, or null for the default package.
     */
    @Nullable
    CgPackage getParentPackage();

    /**
     * Returns the list of subpackages of this package under all source roots of the project.
     *
     * @return the array of subpackages.
     */
    @NotNull
    CgPackage[] getSubPackages();

    /**
     * Returns the list of classes in all directories corresponding to the package.
     *
     * @return the array of classes.
     */
    @NotNull
    CgClass[] getClasses();

    /**
     * Returns the list of package-level annotations for the package.
     *
     * @return the list of annotations, or null if the package does not have any package-level annotations.
     */
    @Nullable
    CgModifierList getAnnotationList();

    /**
     * This method must be invoked on the package after all directories corresponding
     * to it have been renamed/moved accordingly to qualified name change.
     *
     * @param newQualifiedName the new qualified name of the package.
     */
    void handleQualifiedNameChange(@NotNull String newQualifiedName);

    boolean containsClassNamed(@NotNull String name);

}
