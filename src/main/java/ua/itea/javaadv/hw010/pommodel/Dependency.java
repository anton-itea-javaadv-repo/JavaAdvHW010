package ua.itea.javaadv.hw010.pommodel;

public class Dependency {
    /*
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.11</version>
      <scope>test</scope>
    </dependency>
    */

    String groupId;
    String artifactId;
    String version;
    String scope;

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

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    @Override
    public String toString() {
        return "Dependency{" +
                "\n\t\t\tgroupId='" + groupId + '\'' +
                ",\n\t\t\tartifactId='" + artifactId + '\'' +
                ",\n\t\t\tversion='" + version + '\'' +
                ",\n\t\t\tscope='" + scope + '\'' +
                '}';
    }
}
