package com.igitras.codegen.common.next;

import com.igitras.codegen.common.next.annotations.NotNull;
import com.igitras.codegen.common.next.annotations.Nullable;

import java.util.Map;

/**
 * Represents a mapping between type parameters and their values.
 * <p>
 * Created by mason on 1/5/15.
 */
public interface CgSubstitutor {
    /**
     * Returns a mapping that this substitutor contains for a given type parameter.
     * Does not perform bounds promotion
     *
     * @param typeParameter the parameter to return the mapping for.
     * @return the mapping for the type parameter, or <code>null</code> for a raw type.
     */
    @Nullable
    CgType substitute(@NotNull CgTypeParameter typeParameter);

    /**
     * Substitutes type parameters occurring in <code>type</code> with their values.
     * If value for type parameter is <code>null<code>, appropriate erasure is returned.
     *
     * @param type the type to substitute the type parameters for.
     * @return the result of the substitution.
     */
    CgType substitute(@Nullable CgType type);

    //Should be used with great care, be sure to prevent infinite recursion that could arise
    // from the use of recursively bounded type parameters
    CgType substituteWithBoundsPromotion(@NotNull CgTypeParameter typeParameter);

    /**
     * Creates a substitutor instance which provides the specified parameter to type mapping in addition
     * to mappings contained in this substitutor.
     *
     * @param classParameter the parameter which is mapped.
     * @param mapping        the type to which the parameter is mapped.
     * @return the new substitutor instance.
     */
    @NotNull
    CgSubstitutor put(@NotNull CgTypeParameter classParameter, CgType mapping);

    /**
     * Creates a substitutor instance which maps the type parameters of the specified class to the
     * specified types in addition to mappings contained in this substitutor.
     *
     * @param parentClass the class whose parameters are mapped.
     * @param mappings    the types to which the parameters are mapped.
     * @return the new substitutor instance.
     */
    @NotNull
    CgSubstitutor putAll(@NotNull CgClass parentClass, CgType[] mappings);

    /**
     * Creates a substitutor instance containing all mappings from this substitutor and the
     * specified substitutor.
     *
     * @param another the substitutor to get the mappings from.
     * @return the new substitutor instance.
     */
    @NotNull
    CgSubstitutor putAll(@NotNull CgSubstitutor another);

    /**
     * Returns the map from type parameters to types used for substitution by this substitutor.
     *
     * @return the substitution map instance.
     */
    @NotNull
    Map<CgTypeParameter, CgType> getSubstitutionMap();

    /**
     * Checks if all types which the substitutor can substitute are valid.
     *
     * @return true if all types are valid, false otherwise.
     * @see CgType#isValid()
     */
    boolean isValid();

    /**
     * If this substitutor is not valid, throws an exception with some diagnostics
     */
    void ensureValid();
}
