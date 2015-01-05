package com.igitras.codegen.common.next.annotations;

/**
 * Created by mason on 1/5/15.
 */

import java.lang.annotation.*;

/**
 * An element annotated with NotNull claims <code>null</code> value is <em>forbidden</em>
 * to return (for methods), pass to (parameters) and hold (local variables and fields).
 * Apart from documentation purposes this annotation is intended to be used by static analysis tools
 * to validate against probable runtime errors and element contract violations.
 *
 * @author max
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE})
public @interface NotNull {
    /**
     * @return Custom exception message
     */
    String value() default "";

    /**
     * @return Custom exception type that should be thrown when not-nullity contract is violated.
     * The exception class should have a constructor with one String argument (message).
     * <p>
     * By default, {@link java.lang.IllegalArgumentException} is thrown on null method arguments and
     * {@link java.lang.IllegalStateException} &mdash; on null return value.
     */
    Class<? extends Exception> exception() default Exception.class;
}
