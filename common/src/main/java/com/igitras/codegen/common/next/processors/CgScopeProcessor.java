package com.igitras.codegen.common.next.processors;

import com.igitras.codegen.common.next.CgElement;
import com.igitras.codegen.common.next.ResolveState;
import com.igitras.codegen.common.next.annotations.NotNull;

/**
 * Created by mason on 1/5/15.
 */
public interface CgScopeProcessor {
    interface Event {
        Event SET_DECLARATION_HOLDER = new Event() {};
    }

    /**
     * @param element candidate element.
     * @param state   current state of resolver.
     * @return false to stop processing.
     */
    boolean execute(@NotNull CgElement element, @NotNull ResolveState state);

    //    @Nullable
    //    <T> T getHint(@NotNull Key<T> hintKey);
    //
    //    void handleEvent(@NotNull Event event, @Nullable Object associated);
}
