package ua.itea.javaadv.hw010.pommodel;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Build {
    PluginManagement pluginManagement;
    List<Plugin> plugins;

    public PluginManagement getPluginManagement() {
        return pluginManagement;
    }

    public void setPluginManagement(PluginManagement pluginManagement) {
        this.pluginManagement = pluginManagement;
    }

    public List<Plugin> getPlugins() {
        return plugins;
    }

    public void setPlugins(List<Plugin> plugins) {
        this.plugins = plugins;
    }

    @Override
    public String toString() {
        return "Build{" +
                "\n\t\t\tpluginManagement=" + pluginManagement +
                ",\n\t\t\tplugins=" + "\n\t\t\t\t" +
                    plugins.stream()
                            .map(Objects::toString)
                            .collect(Collectors.joining("\n\t\t\t\t")) +
                "\n\t\t\t" + '}';
    }
}
