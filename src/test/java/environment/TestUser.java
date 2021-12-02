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
package environment;

import nl.codevs.strinput.system.api.StrUser;
import nl.codevs.strinput.system.text.Str;
import nl.codevs.strinput.system.text.StrClickable;

import java.util.ArrayList;
import java.util.List;

/**
 * StrUser test implementation.
 * @author Sjoerd van de Goor
 * @since v0.1
 */
public class TestUser implements StrUser {

    public static TestUser SUT = new TestUser();

    public List<String> messages = new ArrayList<>();

    /**
     * The name of the user (something to identify them by).
     *
     * @return the name of the user
     */
    @Override
    public String getName() {
        return "testUser";
    }

    /**
     * Send a message to the user.
     *
     * @param message the message to send
     */
    @Override
    public void sendMessage(Str message) {
        System.out.println(message.toString());
        messages.add(message.toString());
    }

    /**
     * Send multiple options when there is something to choose from.<br>
     * Note that it is required to have a Str.<br>
     * Return {@code null} if an option choice should be forced.
     *
     * @param clickables the clickable options to send
     */
    @Override
    public void sendOptions(List<StrClickable> clickables) {
        for (StrClickable clickable : clickables) {
            sendMessage(clickable);
        }
    }

    /**
     * @return whether this user supports {@link StrClickable}s.
     */
    @Override
    public boolean supportsClickables() {
        return false;
    }

    /**
     * Play a sound effect
     *
     * @param sfx the sound effect type
     */
    @Override
    public void playSound(StrSoundEffect sfx) {

    }

    /**
     * Get the last message this sender received.
     * @return the last message this sender received
     */
    public String getLastMessage() {
        return messages.get(messages.size() - 1);
    }
}
