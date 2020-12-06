package ua.itea.javaadv.hw010.pommodel;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Project {
    String xmlns;
    String xsi;
    String schemaLocation;
    String modelVersion;
    String groupId;
    String artifactId;
    String version;
    String name;
    String url;
    Map<String, String> properties;
    List<Dependency> dependencies;
    Build build;

    public String getXmlns() {
        return xmlns;
    }

    public void setXmlns(String xmlns) {
        this.xmlns = xmlns;
    }

    public String getXsi() {
        return xsi;
    }

    public void setXsi(String xsi) {
        this.xsi = xsi;
    }

    public String getSchemaLocation() {
        return schemaLocation;
    }

    public void setSchemaLocation(String schemaLocation) {
        this.schemaLocation = schemaLocation;
    }

    public String getModelVersion() {
        return modelVersion;
    }

    public void setModelVersion(String modelVersion) {
        this.modelVersion = modelVersion;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    public List<Dependency> getDependencies() {
        return dependencies;
    }

    public void setDependencies(List<Dependency> dependencies) {
        this.dependencies = dependencies;
    }

    public Build getBuild() {
        return build;
    }

    public void setBuild(Build build) {
        this.build = build;
    }

    @Override
    public String toString() {
        return "Project{" +
                "\n\txmlns='" + xmlns + '\'' +
                ",\n\txsi='" + xsi + '\'' +
                ",\n\tschemaLocation='" + schemaLocation + '\'' +
                ",\n\tmodelVersion='" + modelVersion + '\'' +
                ",\n\tgroupId='" + groupId + '\'' +
                ",\n\tartifactId='" + artifactId + '\'' +
                ",\n\tversion='" + version + '\'' +
                ",\n\tname='" + name + '\'' +
                ",\n\turl='" + url + '\'' +
                ",\n\tproperties=[" +
                    properties.entrySet().stream()
                            .map(e -> e.getKey() + " = " + e.getValue())
                            .collect(Collectors.joining(",\n\t\t\t\t")) +
                "],\n\tdependencies=[\n\t\t" +
                    dependencies.stream().map(Objects::toString).collect(Collectors.joining("\n\t\t")) +
                "],\n\tbuild=" + build +
                "\n}";
    }
}
