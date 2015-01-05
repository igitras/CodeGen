package com.igitras.codegen.common.next.processors;

import com.igitras.codegen.common.next.CgElement;
import com.igitras.codegen.common.next.annotations.NotNull;

/**
 * Created by mason on 1/5/15.
 */
public interface CgElementProcessor<T extends CgElement> {
    /**
     * Processes a PsiElement
     *
     * @param element currently processed element.
     * @return false to stop processing.
     */
    boolean execute(@NotNull T element);
}
