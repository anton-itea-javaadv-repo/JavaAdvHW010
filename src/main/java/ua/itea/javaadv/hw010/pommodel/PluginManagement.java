package ua.itea.javaadv.hw010.pommodel;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PluginManagement {
    List<Plugin> plugis;

    public List<Plugin> getPlugis() {
        return plugis;
    }

    public void setPlugis(List<Plugin> plugis) {
        this.plugis = plugis;
    }

    @Override
    public String toString() {
        return "PluginManagement{" +
                "plugins=" + "\n\t\t\t\t" +
                    plugis.stream()
                            .map(Objects::toString)
                            .collect(Collectors.joining("\n\t\t\t\t")) +
                "\n\t\t\t" + '}';
    }
}
