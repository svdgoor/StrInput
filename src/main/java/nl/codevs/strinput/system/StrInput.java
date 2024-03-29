/*
 * This file is part of the StrInput distribution.
 * (https://github.com/CocoTheOwner/StrInput)
 * Copyright (c) 2021 Sjoerd van de Goor.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package nl.codevs.strinput.system;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for commands (methods) and categories (classes).<br>
 * Note that while all settings are optional,
 * {@link #name()} should be specified on a category.
 *
 * @author Sjoerd van de Goor
 * @since v0.1
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface StrInput {

    /**
     * The default description.
     */
    String DEFAULT_DESCRIPTION = "No Description Provided";

    /**
     * The default no-permission string.
     */
    String NO_PERMISSION = "No Permission Required";

    /**
     * The default name.
     */
    String DEFAULT_NAME = "";

    /**
     * The name of this command, which is the Method's name by default.
     * @return the name of the command / category
     */
    String name() default DEFAULT_NAME;

    /**
     * The aliases of this parameter (instead of just the {@link #name() name}
     * (if specified) or Method Name (name of method))<br>
     * Can be initialized as just a string (ex. "alias")
     * or as an array (ex. {"alias1", "alias2"})<br>
     * If someone uses /plugin foo, and you specify alias="f" here,
     * /plugin f will do the exact same.
     *
     * @return the aliases of this {@link StrInput}
     */
    String[] aliases() default "";

    /**
     * The description of this command.<br>
     * Is {@link #DEFAULT_DESCRIPTION} by default
     * @return the description of this {@link StrInput}
     */
    String description() default DEFAULT_DESCRIPTION;

    /**
     * The permissions class that gives
     * the required permission for this command.<p>
     * By default, it requires no permissions
     * @return The permission node for this decree command
     */
    String permission() default NO_PERMISSION;

    /**
     * If the node's functions must be run on the main thread (i.e. sync), set this to true.<br>
     * Defaults to false. Requires you to overwrite the {@link StrCenter#runSync(Runnable)} method.
     *
     * @return true if this category's commands or command should be run in sync
     */
    boolean sync() default false;
}
