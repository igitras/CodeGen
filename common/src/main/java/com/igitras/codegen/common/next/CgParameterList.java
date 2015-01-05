package com.igitras.codegen.common.next;

/**
 * Created by mason on 1/4/15.
 */
public interface CgParameterList extends CgElement {
    /**
     * Returns the array of parameters in the list.
     *
     * @return the array of parameters.
     */
    CgParameter[] getParameters();

    /**
     * Returns the index of the specified parameter in the list.
     *
     * @param parameter the parameter to search for (must belong to this parameter list).
     * @return the index of the parameter.
     */
    int getParameterIndex(CgParameter parameter);

    /**
     * Returns the number of parameters.
     *
     * @return the parameters count
     */
    int getParametersCount();
}
