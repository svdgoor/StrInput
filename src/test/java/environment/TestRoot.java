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
package environment;

import nl.codevs.strinput.system.Param;
import nl.codevs.strinput.system.StrCategory;
import nl.codevs.strinput.system.StrInput;

/**
 * StrCategory test implementation.
 * @author Sjoerd van de Goor
 * @since v0.1
 */
@StrInput(name = "test", description = "Test category")
public class TestRoot implements StrCategory {

    public static String stringAddResult;
    public static int multiplicationResult;

    @StrInput(name = "add", description = "Add two strings")
    public void stringAddition(
            @Param(
                    description = "First string",
                    aliases = "s1",
                    name = "string1"
            ) String stringOne,
            @Param(
                    description = "Second string",
                    defaultValue = "Yeet",
                    aliases = "s2",
                    name = "string2"
            ) String stringTwo
    ) {
        stringAddResult = stringOne + stringTwo;
    }

    @StrInput(description = "Multiply two integers")
    public void multiplication(
            @Param(
                    description = "The first integer",
                    aliases = "input1"
            ) Integer i1,
            @Param(
                    description = "The second integer",
                    aliases = "input2"
            ) Integer i2
    ) {
        multiplicationResult = i1 * i2;
    }

    @StrInput(description = "Multiply two integers")
    public void multiplications(
            @Param(
                    description = "The first integer",
                    aliases = "input1"
            ) Integer i1,
            @Param(
                    description = "The second integer",
                    aliases = "input2"
            ) Integer i2
    ) {
        multiplicationResult = i1 * i2 + 1;
    }

    @StrInput(description = "Complicated multiplication")
    public void complicatedMultiplication(
            @Param (
                    description = "The first value in the multiplication"
            ) int i1,
            @Param (
                    description = "The second value in the multiplication",
                    defaultValue = "3"
            ) int i2,
            @Param (
                    description = "The third value in the multiplication",
                    defaultValue = "3"
            ) int i3,
            @Param (
                    description = "Whether to use i1 in the multiplication",
                    defaultValue = "true"
            ) boolean i1on,
            @Param (
                    description = "Whether to use i2 in the multiplication",
                    defaultValue = "false"
            ) boolean i2on,
            @Param (
                    description = "Whether to use i3 in the multiplication",
                    defaultValue = "false"
            ) boolean i3on
    ) {
        multiplicationResult = (i1on ? i1 : 1) * (i2on ? i2 : 1) * (i3on ? i3 : 1);
    }
}
