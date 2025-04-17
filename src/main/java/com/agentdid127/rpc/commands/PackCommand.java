package com.agentdid127.rpc.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Subcommand;
import com.agentdid127.rpc.Constants;
import com.agentdid127.rpc.VelocityPlugin;
import com.agentdid127.rpc.config.PackConfig;
import com.velocitypowered.api.command.CommandSource;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

import java.io.IOException;

/**
 * /pack command
 */
@CommandAlias("pack")
public class PackCommand extends BaseCommand {

    /**
     * Download a pack
     * @param sender command sender
     */
    @Subcommand("download")
    public void download(CommandSource sender) {
        sender.sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize("&cNot implemented!"));
    }

    /**
     * Get all pack information
     * @param sender command sender
     */
    @Default
    @Subcommand("info")
    public void info(CommandSource sender) {

        // Print pack header
        sender.sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize("&bPacks: "));

        // Print all pack info
        for(PackConfig pack : VelocityPlugin.INSTANCE.getConfig().getPacks()) {
            sender.sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize("&b - server: " + pack.getServer() + ", pack: " + pack.getPack() + ", version: " + pack.getVersion()));
        }
    }

    /**
     * Add a pack to the config
     * @param sender Command sender
     * @param server Server name
     * @param pack Pack url
     * @param version Pack version
     * @throws IOException if configuration fails
     */
    @CommandPermission("rpc.pack")
    @Subcommand("add")
    public void addPack(CommandSource sender, String server, String pack, String version) throws IOException {
        VelocityPlugin.INSTANCE.addPack(server, pack, version);
        sender.sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize("&bPack added to server: " + server));
    }

    /**
     * Remove all packs from a server
     * @param sender Command sender
     * @param server Server name
     * @throws IOException if configuration fails
     */
    @CommandPermission("rpc.pack")
    @Subcommand("remove")
    public void removePack(CommandSource sender, String server) throws IOException {
        VelocityPlugin.INSTANCE.removePack(server);
        sender.sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize("&bPack removed from server: " + server));
    }
}

