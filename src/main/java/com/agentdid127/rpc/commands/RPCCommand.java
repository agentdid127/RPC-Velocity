package com.agentdid127.rpc.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Subcommand;
import com.agentdid127.rpc.Constants;
import com.agentdid127.rpc.VelocityPlugin;
import com.velocitypowered.api.command.CommandSource;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

import java.io.IOException;

/**
 * /rpc command
 */
@CommandAlias("rpc")
public class RPCCommand extends BaseCommand {

    /**
     * Returns info about the plugin
     * @param sender command sender
     */
    @Default
    @Subcommand("info")
    public void info(CommandSource sender) {
        sender.sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize("&bRPCPlugin: v" + Constants.VERSION));
    }

    /**
     * Reloads the plugin's configuration
     * @param sender command sender
     * @throws IOException if configuration fails
     */
    @CommandPermission("rpc.reload")
    @Subcommand("reload")
    public void reloadCommand(CommandSource sender) throws IOException {
        VelocityPlugin.INSTANCE.reloadConfig();
        sender.sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize("&bReloaded config"));
    }
}

