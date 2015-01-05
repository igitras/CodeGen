package com.igitras.codegen.common.next;

import com.igitras.codegen.common.next.annotations.NonNls;
import com.igitras.codegen.common.next.annotations.NotNull;
import com.igitras.codegen.common.next.annotations.Nullable;
import com.igitras.codegen.common.next.processors.CgElementProcessor;

/**
 * Represents a file or directory which can be renamed.
 * <p>
 * Created by mason on 1/5/15.
 */
public interface CgFileSystemItem extends CgCheckedRenameElement {
    boolean isDirectory();

    @Override
    @Nullable
    CgFileSystemItem getParent();

    @NonNls
    @Override
    @NotNull
    @NonNls
    String getName();

    boolean processChildren(CgElementProcessor<CgFileSystemItem> processor);
}
