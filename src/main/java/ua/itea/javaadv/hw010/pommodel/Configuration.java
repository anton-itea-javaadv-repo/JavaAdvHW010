package ua.itea.javaadv.hw010.pommodel;

import java.util.List;

public class Configuration {
    /*
      <plugin>
        <artifactId>maven-assembly-plugin</artifactId>
        <version>3.3.0</version>
        <configuration>
          <descriptorRefs>
            <descriptorRef>jar-with-dependencies</descriptorRef>
          </descriptorRefs>
          <archive>
            <manifest>
              <mainClass>ua.itea.javaadv.hw006.Main</mainClass>
            </manifest>
          </archive>
        </configuration>
        <executions>
          <execution>
            <id>make-assembly</id> <!-- this is used for inheritance merges -->
            <phase>package</phase> <!-- bind to the packaging phase -->
            <goals>
              <goal>single</goal>
            </goals>
          </execution>
        </executions>
      </plugin>
    */

    List<String> descriptorRefs;
    Archive archive;

    public List<String> getDescriptorRefs() {
        return descriptorRefs;
    }

    public void setDescriptorRefs(List<String> descriptorRefs) {
        this.descriptorRefs = descriptorRefs;
    }

    public Archive getArchive() {
        return archive;
    }

    public void setArchive(Archive archive) {
        this.archive = archive;
    }

    @Override
    public String toString() {
        return "Configuration{" +
                "descriptorRefs=" + descriptorRefs +
                ", archive=" + archive +
                '}';
    }
}
