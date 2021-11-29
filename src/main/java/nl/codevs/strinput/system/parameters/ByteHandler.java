/*
 * This file is part of the Strinput distribution (https://github.com/CocoTheOwner/Strinput).
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

package nl.codevs.strinput.system.parameters;

import nl.codevs.strinput.system.exceptions.StrWhichException;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Random;

/**
 * Byte handler.
 *
 * @author Sjoerd van de Goor
 * @since v0.1
 */
public class ByteHandler implements StrParameterHandler<Byte> {

    /**
     * Get all possible values for this type.<br>
     * Do not specify lists of very high length (10^6)
     *
     * @return a list of possibilities
     */
    @Override
    public List<Byte> getPossibilities() {
        return null;
    }

    /**
     * Whether this handler supports the type or not.
     *
     * @param type a type
     *
     * @return true if it supports the type
     */
    @Override
    public boolean supports(@NotNull Class<?> type) {
        return type.equals(Byte.class) || type.equals(byte.class);
    }

    /**
     * Parse a string to this type.
     *
     * @param text the string to parse
     *
     * @return an instance of this type parsed from the string
     *
     * @throws Throwable         when something else fails. (Exceptions don't have to be caught in the parser)
     */
    @Override
    public @NotNull Byte parse(@NotNull String text) throws Throwable {
        return Byte.parseByte(text);
    }

    /**
     * Get a random default value.
     *
     * @return the random default
     */
    @Override
    public @NotNull String getRandomDefault() {
        return String.valueOf(RANDOM.nextInt(Byte.MIN_VALUE, Byte.MAX_VALUE));
    }
}