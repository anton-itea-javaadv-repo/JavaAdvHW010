package ua.itea.javaadv.hw010;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import ua.itea.javaadv.hw010.pommodel.Archive;
import ua.itea.javaadv.hw010.pommodel.Build;
import ua.itea.javaadv.hw010.pommodel.Configuration;
import ua.itea.javaadv.hw010.pommodel.Dependency;
import ua.itea.javaadv.hw010.pommodel.Execution;
import ua.itea.javaadv.hw010.pommodel.Manifest;
import ua.itea.javaadv.hw010.pommodel.Plugin;
import ua.itea.javaadv.hw010.pommodel.PluginManagement;
import ua.itea.javaadv.hw010.pommodel.Project;

import java.util.ArrayList;
import java.util.HashMap;

public class SimplePomParser extends DefaultHandler {
    private static final String PROJECT = "project";
    private static final String XMLNS = "xmlns";
    private static final String XSI = "xmlns:xsi";
    private static final String SCHEMA_LOCATION = "xsi:schemaLocation";
    private static final String MODEL_VERSION = "modelVersion";
    private static final String GROUP_ID = "groupId";
    private static final String ARTIFACT_ID = "artifactId";
    private static final String VERSION = "version";
    private static final String SCOPE = "scope";
    private static final String NAME = "name";
    private static final String URL = "url";
    private static final String PROPERTIES = "properties";
    private static final String PROP_SOURCE_ENCODING = "project.build.sourceEncoding";
    private static final String PROP_COMPILER_SOURCE = "maven.compiler.source";
    private static final String PROP_COMPILER_TARGET = "maven.compiler.target";
    private static final String DEPENDENCIES = "dependencies";
    private static final String DEPENDENCY = "dependency";
    private static final String BUILD = "build";
    private static final String PLUGIN_MANAGEMENT = "pluginManagement";
    private static final String PLUGINS = "plugins";
    private static final String PLUGIN = "plugin";
    private static final String CONFIGURATION = "configuration";
    private static final String DESCRIPTOR_REFS = "descriptorRefs";
    private static final String DESCRIPTOR_REF = "descriptorRef";
    private static final String ARCHIVE = "archive";
    private static final String MANIFEST = "manifest";
    private static final String MAIN_CLASS = "mainClass";
    private static final String EXECUTIONS = "executions";
    private static final String EXECUTION = "execution";
    private static final String ID = "id";
    private static final String PHASE = "phase";
    private static final String GOALS = "goals";
    private static final String GOAL = "goal";

    private Project project;
    private String currentNode;
    private int level = 0;
    private boolean build = false;
    private boolean pluginManagement = false;

    private Dependency currentDependency;
    private Plugin currentPlugin;
    private Execution currentExecution;

    public Project getProject() {
        return project;
    }

    @Override
    public void startDocument() throws SAXException {
        System.out.println("start");
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("end");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        level++;
        currentNode = qName;
        switch (qName) {
            case PROJECT: {
                project = new Project();
                for (int i = 0; i < attributes.getLength(); i++) {
                    String qNameOfAttr = attributes.getQName(i);
                    String value = attributes.getValue(i);

                    switch (qNameOfAttr) {
                        case XMLNS: {
                            project.setXmlns(value);
                            break;
                        }
                        case XSI: {
                            project.setXsi(value);
                            break;
                        }
                        case SCHEMA_LOCATION: {
                            project.setSchemaLocation(value);
                            break;
                        }
                        default: {
                            throw new RuntimeException("No action for " + qNameOfAttr + " attribute!");
                        }
                    }
                }
                break;
            }
            case PROPERTIES: {
                if (project.getProperties() == null) {
                    project.setProperties(new HashMap<>());
                }
                break;
            }
            case DEPENDENCIES: {
                if (project.getDependencies() == null) {
                    project.setDependencies(new ArrayList<>());
                }
                break;
            }
            case DEPENDENCY: {
                currentDependency = new Dependency();
                project.getDependencies().add(currentDependency);
                break;
            }
            case BUILD: {
                build = true;
                if (project.getBuild() == null) {
                    project.setBuild(new Build());
                }
                break;
            }
            case PLUGIN_MANAGEMENT: {
                pluginManagement = true;
                if (project.getBuild().getPluginManagement() == null) {
                    project.getBuild().setPluginManagement(new PluginManagement());
                }
                break;
            }
            case PLUGINS: {
                if (pluginManagement) {
                    project.getBuild().getPluginManagement().setPlugis(new ArrayList<>());
                } else {
                    project.getBuild().setPlugins(new ArrayList<>());
                }
                break;
            }
            case PLUGIN: {
                currentPlugin = new Plugin();
                if (pluginManagement) {
                    project.getBuild().getPluginManagement().getPlugis().add(currentPlugin);
                } else {
                    project.getBuild().getPlugins().add(currentPlugin);
                }
                break;
            }
            case CONFIGURATION: {
                currentPlugin.setConfiguration(new Configuration());
                break;
            }
            case EXECUTIONS: {
                currentPlugin.setExecutions(new ArrayList<>());
                break;
            }
            case EXECUTION: {
                currentExecution = new Execution();
                currentPlugin.getExecutions().add(currentExecution);
                break;
            }
            case DESCRIPTOR_REFS: {
                currentPlugin.getConfiguration().setDescriptorRefs(new ArrayList<>());
                break;
            }
            case ARCHIVE: {
                currentPlugin.getConfiguration().setArchive(new Archive());
                break;
            }
            case MANIFEST: {
                currentPlugin.getConfiguration().getArchive().setManifest(new Manifest());
                break;
            }
            case GOALS: {
                currentExecution.setGoals(new ArrayList<>());
                break;
            }
            default: {
//                throw new RuntimeException("No action for " + qName + " element!");
                System.out.println("startElement: " + qName);
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String value = new String(ch, start, length).replace("\n", "").trim();
        if (!value.isEmpty()) {
            switch (currentNode) {
                case MODEL_VERSION: {
                    project.setModelVersion(value);
                    break;
                }
                case GROUP_ID: {
                    if (level == 2) {
                        project.setGroupId(value);
                    } else if (level == 4 && currentDependency != null) {
                        currentDependency.setGroupId(value);
                    }
                    break;
                }
                case ARTIFACT_ID: {
                    if (level == 2) {
                        project.setArtifactId(value);
                    } else if (level == 4 && currentDependency != null) {
                        currentDependency.setArtifactId(value);
                    } else if (build) {
                        currentPlugin.setArtifactId(value);
                    }
                    break;
                }
                case VERSION: {
                    if (level == 2) {
                        project.setVersion(value);
                    } else if (level == 4 && currentDependency != null) {
                        currentDependency.setVersion(value);
                    } else if (build) {
                        currentPlugin.setVersion(value);
                    }
                    break;
                }
                case SCOPE: {
                    if (level == 4 && currentDependency != null) {
                        currentDependency.setScope(value);
                    }
                    break;
                }
                case NAME: {
                    if (level == 2) {
                        project.setName(value);
                    }
                    break;
                }
                case URL: {
                    if (level == 2) {
                        project.setUrl(value);
                    }
                    break;
                }
                case PROP_SOURCE_ENCODING: {
                    if (level == 3) {
                        project.getProperties().put(PROP_SOURCE_ENCODING, value);
                    }
                    break;
                }
                case PROP_COMPILER_SOURCE: {
                    if (level == 3) {
                        project.getProperties().put(PROP_COMPILER_SOURCE, value);
                    }
                    break;
                }
                case PROP_COMPILER_TARGET: {
                    if (level == 3) {
                        project.getProperties().put(PROP_COMPILER_TARGET, value);
                    }
                    break;
                }
                case DESCRIPTOR_REF: {
                    currentPlugin.getConfiguration().getDescriptorRefs().add(value);
                    break;
                }
                case MAIN_CLASS: {
                    currentPlugin.getConfiguration().getArchive().getManifest().setMainClass(value);
                    break;
                }
                case ID: {
                    currentExecution.setId(value);
                    break;
                }
                case PHASE: {
                    currentExecution.setPhase(value);
                    break;
                }
                case GOAL: {
                    currentExecution.getGoals().add(value);
                    break;
                }
                default: {
//                    throw new RuntimeException("No action for " + currentNode + " node! Value is: \"" + value + "\"");
                    System.out.println("characters value: " + value);
                }
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        level--;
        if (DEPENDENCIES.equals(qName)) {
            currentDependency = null;
        } else if (BUILD.equals(qName)) {
            build = false;
        } else if (PLUGIN_MANAGEMENT.equals(qName)) {
            pluginManagement = false;
        } else if (PLUGINS.equals(qName)) {
            currentPlugin = null;
        }
        System.out.println("endElement: " + qName);
    }
}
