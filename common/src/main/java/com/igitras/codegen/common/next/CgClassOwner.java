package com.igitras.codegen.common.next;

import com.igitras.codegen.common.next.annotations.NotNull;
import com.igitras.codegen.common.next.exceptions.IncorrectOperationException;

/**
 * Created by mason on 1/5/15.
 */
public interface CgClassOwner extends CgFile {
    /**
     * @return classes owned by this element.
     */
    @NotNull
    CgClass[] getClasses();

    /**
     * Returns the name of the package to which the file belongs.
     *
     * @return the name specified in the package statement, or an empty string for a JSP page or
     * file which has no package statement.
     */
    String getPackageName();

    void setPackageName(String packageName) throws IncorrectOperationException;
}
