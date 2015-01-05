package com.igitras.codegen.common.next;

import com.igitras.codegen.common.next.annotations.NotNull;

/**
 * Created by mason on 1/5/15.
 */
public interface CgDirectoryContainer extends CgNamedElement {
    /**
     * Returns the array of all directories (under all source roots in the project)
     * corresponding to the package.
     *
     * @return the array of directories.
     */
    @NotNull
    CgDirectory[] getDirectories();

}
