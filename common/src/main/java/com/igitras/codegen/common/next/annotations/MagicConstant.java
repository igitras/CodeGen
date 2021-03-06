package com.igitras.codegen.common.next.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>This annotation intended to help IDEA to detect and auto-complete int and String constants used as an enumeration.
 * For example, in the {@link java.awt.Label#Label(String, int)} constructor the <tt><b>alignment</b></tt> parameter can be one of the following
 * int constants: {@link java.awt.Label#LEFT}, {@link java.awt.Label#CENTER} or {@link java.awt.Label#RIGHT}
 * <p>
 * <p>So, if <tt>@MagicConstant</tt> annotation applied to this constructor, IDEA will check the constructor usages for the allowed values.
 * <p>E.g.<br>
 * <pre>{@code
 * new Label("text", 0); // 0 is not allowed
 * new Label("text", Label.LEFT); // OK
 * }</pre>
 * <p>
 * <p>
 * <tt>@MagicConstant</tt> can be applied to:
 * <ul>
 * <li> Field, local variable, parameter.
 * <p>
 * <br>E.g. <br>
 * <pre>{@code @MagicConstant(intValues = {TOP, CENTER, BOTTOM})
 * int textPosition;
 * }</pre>
 * IDEA will check expressions assigned to the variable for allowed values:
 * <pre>{@code
 *  textPosition = 0; // not allowed
 *  textPosition = TOP; // OK
 * }</pre>
 * <p>
 * <li> Method
 * <p>
 * <br>E.g.<br>
 * <pre>{@code @MagicConstant(flagsFromClass = java.lang.reflect.Modifier.class)
 *  public native int getModifiers();
 * }</pre>
 * <p>
 * IDEA will analyse getModifiers() method calls and check if its return value is used with allowed values:<br>
 * <pre>{@code
 *  if (aClass.getModifiers() == 3) // not allowed
 *  if (aClass.getModifiers() == Modifier.PUBLIC) // OK
 * }</pre>
 * <p>
 * <li>Annotation class<br>
 * Annotation class annotated with <tt>@MagicConstant</tt> created alias you can use to annotate
 * everything as if it was annotated with <tt>@MagicConstant</tt> itself.
 * <p>
 * <br>E.g.<br>
 * <pre>{@code @MagicConstant(flags = {Font.PLAIN, Font.BOLD, Font.ITALIC}) }</pre>
 * <pre>{@code @interface FontStyle {} }</pre>
 * <p>
 * IDEA will check constructs annotated with @FontStyle for allowed values:<br>
 * <tt>@FontStyle int myStyle = 3; // not allowed<br></tt>
 * <tt>@FontStyle int myStyle = Font.BOLD | Font.ITALIC; // OK</tt><br>
 * </tt>
 * <p>
 * </ul>
 * <p>
 * The <tt>@MagicConstant</tt> annotation has SOURCE retention, i.e. it is removed upon compilation and does not create any runtime overhead.
 */
@Retention(RetentionPolicy.SOURCE)
@Target({
                ElementType.FIELD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.ANNOTATION_TYPE,
                ElementType.METHOD
        })
public @interface MagicConstant {
    /**
     * @return int values (typically named constants) which are allowed here.
     * E.g.
     * <tt><pre>
     * {@code
     * void setConfirmOpenNewProject(@MagicConstant(intValues = {OPEN_PROJECT_ASK, OPEN_PROJECT_NEW_WINDOW, OPEN_PROJECT_SAME_WINDOW})
     *                               int confirmOpenNewProject);
     * }</pre></tt>
     */
    long[] intValues() default {};

    /**
     * @return String values (typically named constants) which are allowed here.
     */
    String[] stringValues() default {};

    /**
     * @return allowed int flags (i.e. values (typically named constants) which can be combined with bitwise or operator (|).
     * Also 0 and -1 are considered allowed.
     * E.g.
     * <tt><pre>
     * {@code @MagicConstant(flags = {HierarchyEvent.PARENT_CHANGED,HierarchyEvent.DISPLAYABILITY_CHANGED,HierarchyEvent.SHOWING_CHANGED})
     * int hFlags;
     * <p>
     * hFlags = 3; // not allowed
     * if (hFlags & (HierarchyEvent.PARENT_CHANGED | HierarchyEvent.SHOWING_CHANGED) != 0); // OK
     * }</pre></tt>
     */
    long[] flags() default {};

    /**
     * @return allowed values which are defined in the specified class public static final constants.
     * <p>
     * E.g.
     * <tt><pre>
     * {@code @MagicConstant(valuesFromClass = Cursor.class)
     * int cursorType;
     * <p>
     * cursorType = 11; // not allowed;
     * cursorType = Cursor.E_RESIZE_CURSOR; // OK
     * }</pre></tt>
     */
    Class valuesFromClass() default void.class;

    /**
     * @return allowed int flags which are defined in the specified class public static final constants.
     * <p>
     * E.g.
     * <tt><pre>
     * {@code @MagicConstant(flagsFromClass = java.awt.InputEvent.class)
     * int eventMask;
     * <p>
     * eventMask = 10; // not allowed;
     * eventMask = InputEvent.CTRL_MASK | InputEvent.ALT_MASK; // OK
     * }</pre></tt>
     */
    Class flagsFromClass() default void.class;
}
