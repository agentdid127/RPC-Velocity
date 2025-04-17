package com.agentdid127.rpc.config;

import java.util.ArrayList;
import java.util.List;

/**
 * Main configuration file
 */
public class Config {

    // All packs
    List<PackConfig> packs;

    /**
     * Construct a new Config
     */
    public Config() {
        this.packs = new ArrayList<>();
    }

    /**
     * Sets packs to a new PackConfig
     * @param packs PackConfig packs
     */
    public void setPacks(List<PackConfig> packs) {
        this.packs = packs;
    }

    /**
     * Gets the packs
     * @return the packs
     */
    public List<PackConfig> getPacks() {
        return packs;
    }

    @Override
    public String toString() {
        String out = "Config{\n";
        for (PackConfig pack : packs) {
            out += "  " + pack.toString() + ",\n";
        }
        out += "}\n";
        return out;
    }
}
