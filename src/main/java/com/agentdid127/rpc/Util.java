package com.agentdid127.rpc;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Util {

    /**
     * Loads a YAML configuration file
     * @param path Configuration directory
     * @param file Config file
     * @param clazz Class of Config
     * @return The config object
     * @param <T> Type of config
     * @throws IOException if configuration fails
     */
    public static <T> T loadYAMLConfig(String path, String file, Class clazz) throws IOException {
        Path pluginDataPath = Paths.get(path);

        if (!pluginDataPath.toFile().exists()) {
            pluginDataPath.toFile().mkdirs();
        }

        Path filePath = pluginDataPath.resolve(file);

        if (!filePath.toFile().exists()) {
            return null;
        }
        Yaml yaml = new Yaml(new Constructor(clazz, new LoaderOptions()));
        return yaml.load(new FileReader(filePath.toFile()));
    }

    /**
     * Stores the YAML configuration
     * @param path Configuration directory
     * @param file Configuration file
     * @param data Data to store
     * @param <T> Type of data to store
     * @throws IOException if storage fails
     */
    public static <T> void storeYAMLConfig(String path, String file, T data) throws IOException {
        Path pluginDataPath = Paths.get(path);
        if (!pluginDataPath.toFile().exists()) {
            pluginDataPath.toFile().mkdirs();
        }

        Yaml yaml = new Yaml(new Constructor(data.getClass(), new LoaderOptions()));
        FileWriter fileWriter = new FileWriter(pluginDataPath.resolve(file).toFile());
        yaml.dump(data, fileWriter);
        fileWriter.close();
    }
}
