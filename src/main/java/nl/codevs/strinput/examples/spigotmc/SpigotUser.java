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
package nl.codevs.strinput.examples.spigotmc;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import nl.codevs.strinput.system.Context;
import nl.codevs.strinput.system.StrUser;
import nl.codevs.strinput.system.util.C;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Random;

/**
 * Spigot user implementation.
 *
 * @param player the underlying Minecraft {@link Player}
 * @author Sjoerd van de Goor
 * @since v0.1
 */
public record SpigotUser(Player player) implements StrUser {

    /**
     * Get the player.
     *
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * The name of the user (something to identify them by).
     *
     * @return the name of the user
     */
    @Override
    public @NotNull String getName() {
        return player.getName();
    }

    /**
     * Send a message to the user.
     *
     * @param message the message to send
     */
    @Override
    public void sendMessage(@NotNull TextComponent message) {
        if (Context.center() instanceof SpigotCenter spigotCenter) {
            spigotCenter.getAudiences().player(player()).sendMessage(message);
        } else {
            throw new RuntimeException("Found non-SpigotCenter for SpigotUser!");
        }
    }

    /**
     * Send a message to the user.
     *
     * @param message the message to send
     */
    @Override
    public void sendMessage(@NotNull String message) {
        List<String> colorSplit = C.splitByC(message);
        TextComponent tMessage = Component.text("");
        for (int i = 0; i < colorSplit.size(); i += 2) {
            C color = C.fromCode(colorSplit.get(i).charAt(0));
            if (color == null) {
                color = C.RESET;
            }
            tMessage = tMessage.append(Component.text(colorSplit.get(i + 1)).color(switch (color) {
                case RED -> NamedTextColor.RED;
                case GREEN -> NamedTextColor.GREEN;
                case BLUE -> NamedTextColor.BLUE;
                case YELLOW -> NamedTextColor.YELLOW;
                case RESET -> NamedTextColor.GRAY;
            }));
        }
        sendMessage(tMessage);
    }

    /**
     * @return whether this user supports clickable messages.
     */
    @Override
    public boolean replaceClickable() {
        return false;
    }

    /**
     * Play a sound effect.
     *
     * @param sfx the sound effect type
     */
    @Override
    public void playSound(final @NotNull StrSoundEffect sfx) {
        switch (sfx) {
            case SUCCESSFUL_TAB ->
                player.playSound(
                        player.getLocation(),
                        Sound.BLOCK_AMETHYST_BLOCK_CHIME,
                        0.25f,
                        (float) (new Random().nextInt(182) + 13) / 100
                );
            case FAILED_TAB ->
                player.playSound(
                        player.getLocation(),
                        Sound.BLOCK_AMETHYST_BLOCK_BREAK,
                        0.25f,
                        (float) (new Random().nextInt(182) + 13) / 100
                );
            case SUCCESSFUL_COMMAND -> {
                player.playSound(
                        player.getLocation(),
                        Sound.BLOCK_AMETHYST_CLUSTER_BREAK,
                        0.77f,
                        1.65f
                );
                player.playSound(
                        player.getLocation(),
                        Sound.BLOCK_RESPAWN_ANCHOR_CHARGE,
                        0.125f,
                        2.99f
                );
            }
            case FAILED_COMMAND -> {
                player.playSound(
                        player.getLocation(),
                        Sound.BLOCK_ANCIENT_DEBRIS_BREAK,
                        1f,
                        0.25f
                );
                player.playSound(
                        player.getLocation(),
                        Sound.BLOCK_RESPAWN_ANCHOR_DEPLETE,
                        0.2f,
                        1.95f
                );
            }
            case SUCCESSFUL_PICKED ->
                    player.playSound(
                            player.getLocation(),
                            Sound.BLOCK_BEACON_ACTIVATE,
                            0.125f,
                            1.99f
                    );
            case FAILED_PICKED -> {
                player.playSound(
                        player.getLocation(),
                        Sound.BLOCK_AMETHYST_CLUSTER_BREAK,
                        0.77f,
                        0.65f
                );
                player.playSound(
                        player.getLocation(),
                        Sound.BLOCK_BEACON_DEACTIVATE,
                        0.125f,
                        1.99f
                );
            }
            default -> throw new IllegalArgumentException(
                    "Unhandled sound effect: " + sfx.name()
            );
        }
    }

    /**
     * If this sender supports context,
     * i.e. has values it stores for getting data automatically
     * (instead of specifying it in commands).
     *
     * @return true if the user supports context
     */
    @Override
    public boolean supportsContext() {
        return true;
    }

    /**
     * Whether this user has permission for a certain node or not.
     *
     * @param permission the permissions node
     * @return true if permitted.
     */
    @Override
    public boolean hasPermission(final @NotNull String permission) {
        return player.hasPermission(permission);
    }

    /**
     * @return Whether this user is a player or not.
     */
    public boolean isPlayer() {
        return player == null;
    }
}
