package com.agentdid127.rpc;

import co.aikar.commands.VelocityCommandManager;
import com.agentdid127.rpc.commands.PackCommand;
import com.agentdid127.rpc.commands.RPCCommand;
import com.agentdid127.rpc.config.Config;
import com.agentdid127.rpc.config.PackConfig;
import com.agentdid127.rpc.listeners.ServerJoinListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import org.slf4j.Logger;

import java.io.IOException;

/**
 * Velocity Plugin
 */
@Plugin(id="rpc-velocity", name= Constants.NAME, version = Constants.VERSION)
public class VelocityPlugin {

    public static final String CONFIG_PATH = "./plugins/rpc-velocity/";
    public static VelocityPlugin INSTANCE;

    private final ProxyServer proxy;
    private final Logger logger;

    private final Gson gson;
    private Config config;

    /**
     * Velocity Plugin
     * @param server ProxyServer
     * @param logger Logger
     */
    @Inject
    public VelocityPlugin(ProxyServer server, Logger logger) {
        this.proxy = server;
        this.logger = logger;
        INSTANCE = this;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    /**
     * Initialize Plugin
     * @param event initialize event
     * @throws IOException If configuration fails
     */
    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) throws IOException {
        logger.warn(Constants.NAME + " initialized");

        // Reload and log the config
        reloadConfig();
        logger.info("Config: {}", config);

        // Events
        proxy.getEventManager().register(this, new ServerJoinListener());

        // Commands
        VelocityCommandManager commandManager = new VelocityCommandManager(proxy, this);
        commandManager.registerCommand(new RPCCommand());
        commandManager.registerCommand(new PackCommand());
    }

    /**
     * Add a Pack Configuration
     * @param server Server to give the user the pack
     * @param pack Pack to give the user
     * @param version Version of the pack
     * @throws IOException If configuration changes fail
     */
    public void addPack(String server, String pack, String version) throws IOException {
        PackConfig packConfig = new PackConfig();
        packConfig.setServer(server);
        packConfig.setPack(pack);
        packConfig.setVersion(version);

        config.getPacks().add(packConfig);
        Util.storeYAMLConfig(CONFIG_PATH, "config.yml", config);
    }

    /**
     * Removes all packs from a server
     * @param server Server to remove packs from
     * @throws IOException If configuration fails
     */
    public void removePack(String server) throws IOException {
        config.getPacks().removeIf(pack -> pack.getServer().equals(server));
        Util.storeYAMLConfig(CONFIG_PATH, "config.yml", config);
    }

    /**
     * Reloads the configuration
     * @throws IOException If reload fails
     */
    public void reloadConfig() throws IOException {

        // Try to do it normally
        config = Util.loadYAMLConfig(CONFIG_PATH, "config.yml", Config.class);
        // If no config, generate a new one
        if (config == null) {
            config = new Config();
            Util.storeYAMLConfig(CONFIG_PATH, "config.yml", config);
        }
    }

    /**
     * Runs when the proxy closes
     * @param event ProxyShutdownEvent
     */
    @Subscribe
    public void onProxyShutdown(ProxyShutdownEvent event) {
        logger.warn(Constants.NAME + " shutdown");
    }

    /**
     * Returns the config
     * @return The config
     */
    public Config getConfig() {
        return config;
    }

    /**
     * Gets the proxy
     * @return the proxy
     */
    public ProxyServer getProxy() {
        return proxy;
    }

    /**
     * Gets the GSON
     * @return gson
     */
    public Gson getGson() {
        return gson;
    }

    /**
     * Gets the logger
     * @return logger
     */
    public Logger getLogger() {
        return logger;
    }

}