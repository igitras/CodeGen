package com.igitras.codegen.common.next;

import com.igitras.codegen.common.next.annotations.MagicConstant;

/**
 * Provides a list of possible modifier keywords for Java classes, methods and fields.
 * <p>
 * Created by mason on 1/4/15.
 */
public interface CgModifier {
    String PUBLIC = "public";
    String PROTECTED = "protected";
    String PRIVATE = "private";
    String PACKAGE_LOCAL = "packageLocal";
    String STATIC = "static";
    String ABSTRACT = "abstract";
    String FINAL = "final";
    String NATIVE = "native";
    String SYNCHRONIZED = "synchronized";
    String STRICTFP = "strictfp";
    String TRANSIENT = "transient";
    String VOLATILE = "volatile";
    String DEFAULT = "default";

    String[] MODIFIERS = {
            PUBLIC, PROTECTED, PRIVATE, STATIC, ABSTRACT, FINAL, NATIVE, SYNCHRONIZED, STRICTFP, TRANSIENT, VOLATILE,
            DEFAULT
    };

    @MagicConstant(stringValues = {
            PUBLIC, PROTECTED, PRIVATE, STATIC, ABSTRACT, FINAL, NATIVE, SYNCHRONIZED, STRICTFP, TRANSIENT, VOLATILE,
            DEFAULT, PACKAGE_LOCAL
    })
    @interface ModifierConstant {}
}
