package org.collebol.client.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Load your own game info. Make sure to setup the following plugins in your <code>pom.xml</code>.
 *
 * <p>Usage:</p>
 * <pre>{@code
 * <build>
 *     <resources>
 *         <resource>
 *             <directory>src/main/resources/properties</directory>
 *             <filtering>true</filtering>
 *         </resource>
 *     </resources>
 *     <plugins>
 *         <plugin>
 *             <groupId>org.apache.maven.plugins</groupId>
 *             <artifactId>maven-resources-plugin</artifactId>
 *             <version>3.3.1</version>
 *             <configuration>
 *                 <delimiters>
 *                     <delimiter>${*}</delimiter>
 *                 </delimiters>
 *                 <useDefaultDelimiters>true</useDefaultDelimiters>
 *             </configuration>
 *         </plugin>
 *     </plugins>
 * </build>
 * }</pre>
 *
 * <p>Steps to add your own game info:</p>
 * <ol>
 *   <li>Add your <code>game.properties</code> to the directory you have set at: <code>src/main/resources/properties</code>.</li>
 *   <li>Use resource filtering in Maven so variables like <code>${project.version}</code> are replaced.</li>
 * </ol>
 *
 * <p>Add the following to your <code>game.properties</code>:</p>
 * <blockquote><pre>
 *     version=${project.version}
 *     artifactId=${project.artifactId}
 *     name=${project.name}
 * </pre></blockquote>
 *
 * @author ColleBol - <a href="mailto:contact@collebol.org">contact@collebol.org</a>
 * @since 1.0-dev
 */
public abstract class GameInfo {

    private final Properties properties = new Properties();

    public GameInfo(String path) {
        try (InputStream in = getClass().getResourceAsStream(path)) {
            if (in != null) {
                properties.load(in);
            } else {
                System.err.println("Game properties not found!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getVersion() {
        return properties.getProperty("version", "unknown");
    }

    public String getArtifactId() {
        return properties.getProperty("artifactId", "unknown");
    }

    public String getName() {
        return properties.getProperty("name", "unknown");
    }

}
