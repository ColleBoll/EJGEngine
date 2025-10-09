package org.collebol.client.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Info of the engine in the ejgengine.properties file.
 *
 * @author ColleBol - <a href="mailto:contact@collebol.org">contact@collebol.org</a>
 * @since 1.0-dev
 */
public class EngineInfo {

    private final Properties properties = new Properties();

    public EngineInfo() {
        try (InputStream in = getClass().getResourceAsStream("/ejgengine.properties")) {
            if (in != null) {
                properties.load(in);
            } else {
                System.err.println("ejgengine.properties not found!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getVersion() {
        return properties.getProperty("engine.version", "unknown");
    }

    public String getArtifactId() {
        return properties.getProperty("engine.artifactId", "unknown");
    }

    public String getName() {
        return properties.getProperty("engine.name", "unknown");
    }

    public String getBuildTime() {
        return properties.getProperty("engine.build.time", "unknown");
    }

    public String getUrl() {
        return properties.getProperty("engine.url", "unknown");
    }

    public String getAuthor() {
        return properties.getProperty("engine.author", "unknown");
    }

    public String getAuthorEmail() {
        return properties.getProperty("engine.author.email", "unknown");
    }
}
