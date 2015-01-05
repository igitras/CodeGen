package com.igitras.codegen.common.next;

import com.igitras.codegen.common.next.annotations.NonNls;

/**
 * Created by mason on 1/5/15.
 */
public interface CgMetaData {
    CgElement getDeclaration();

    @NonNls
    String getName(CgElement context);

    @NonNls
    String getName();

    void init(CgElement element);
}
