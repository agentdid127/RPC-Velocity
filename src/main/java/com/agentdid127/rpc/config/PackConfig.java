package com.agentdid127.rpc.config;

/**
 * A single Pack Configuration
 */
public class PackConfig {

    private String server;
    private String pack;
    private String version;

    /**
     * Constructor, Generates an empty pack
     */
    public PackConfig() {
        this.server = "Lobby";
        this.pack = "";
        this.version = "1.21.4";
    }

    /**
     * Sets the server
     * @param server server name
     */
    public void setServer(String server) {
        this.server = server;
    }
    /**
     * Gets the server
     * @return Server name
     */
    public String getServer() {
        return server;
    }

    /**
     * Sets the pack url
     * @param pack new url
     */
    public void setPack(String pack) {
        this.pack = pack;
    }

    /**
     * Gets the Pack URL
     * @return pack url
     */
    public String getPack() {
        return pack;
    }

    /**
     * Sets the version
     * @param version Pack version
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * Returns the version
     * @return version
     */
    public String getVersion() {
        return version;
    }

    /**
     * Prints object as a string
     * @return String object
     */
    @Override
    public String toString() {
        return "Pack{pack=" + getPack() + ", server=" + getServer() + ", version=" + getVersion() + '}';
    }
}
