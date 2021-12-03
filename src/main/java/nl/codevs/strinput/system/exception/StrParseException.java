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
package nl.codevs.strinput.system.exception;

import lombok.Getter;
import nl.codevs.strinput.system.context.StrContextHandler;
import nl.codevs.strinput.system.text.C;
import nl.codevs.strinput.system.text.Str;

/**
 * Thrown when a decree parameter is parsed, but parsing fails
 * @author Sjoerd van de Goor
 * @since v0.1
 */
@Getter
public class StrParseException extends Exception {
    private final Class<?> type;
    private final Str input;
    private final Str reason;
    private final Str message;

    public StrParseException(Class<?> type, String input, Throwable reason) {
        this(type, input, reason.getClass().getSimpleName() + " - " + reason.getMessage());
    }
    public StrParseException(Class<?> type, String input, String reason) {
        super();
        this.type = type;
        this.input = new Str(input);
        this.reason = new Str(reason);
        this.message = new Str(C.GOLD).a("Could not parse ").a(C.GOLD).a(input).a(C.R).a(" (").a(C.GOLD).a(type.getSimpleName()).a(C.R).a(") because of: ").a(C.GOLD).a(reason);
    }
}