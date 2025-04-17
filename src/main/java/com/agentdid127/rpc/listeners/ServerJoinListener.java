package com.agentdid127.rpc.listeners;

import com.agentdid127.resourcepack.library.utilities.Util;
import com.agentdid127.rpc.VelocityPlugin;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.player.ServerPostConnectEvent;
import com.velocitypowered.api.proxy.player.ResourcePackInfo;


/**
 * Server Join Listener
 */
public class ServerJoinListener {

    /**
     * Gives the user a pack on join
     * @param event the join event
     */
    @Subscribe
    public void onJoinServer(ServerPostConnectEvent event) {

        // Iterate through all packs
        VelocityPlugin.INSTANCE.getConfig().getPacks().forEach(pack -> {

            // If you are on the right server, give them the pack
            if (pack.getServer().equals(event.getPlayer().getCurrentServer().get().getServerInfo().getName())) {
                event.getPlayer().clearResourcePacks();

                // If the version is native, give it directly
                if (event.getPlayer().getProtocolVersion().getProtocol() == Util.getVersionProtocol(VelocityPlugin.INSTANCE.getGson(), pack.getVersion())) {
                    ResourcePackInfo packInfo = VelocityPlugin.INSTANCE.getProxy().createResourcePackBuilder(pack.getPack()).build();
                    event.getPlayer().sendResourcePackOffer(packInfo);
                }

                //TODO: Convert automatically, and give pack
            }
        });
    }
}
