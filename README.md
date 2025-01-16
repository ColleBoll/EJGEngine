# EJGEngine

EJGEngine is a game engine designed to be used on **Windows** or **macOS** devices. It is currently in an early development state, so the project structure may change frequently.

## Features

- Cross-platform support (Windows and macOS)
- Written in Java
- Uses LWJGL for graphics and audio

## Getting Started

### Prerequisites

- **Java 17** or higher: [Download from Oracle](https://www.oracle.com/java/technologies/downloads/)
- **Maven**: [Download from Apache](https://maven.apache.org/download.cgi)
- **IntelliJ IDEA** (recommended) or another IDE

### Installation
> [!TIP]
> Read the full documentation of the use on the [wiki](https://github.com/ColleBoll/EJGEngine/wiki).

1. **Set up your Maven `settings.xml`:**
    ```xml
    <settings
        <servers>
            <server>
                <id>github</id>
                <username>YOUR-GITHUB-USERNAME</username>
                <password>YOUR-GITHUB-TOKEN</password>
            </server>
        </servers>
    </settings>
    ```

2. **Add the EJGEngine dependency to your `pom.xml`:**
    ```xml
    <repositories>
        <repository>
            <id>github</id>
            <url>https://maven.pkg.github.com/ColleBoll/EJGEngine</url>
        </repository>
    </repositories>

    <dependencies>
        <dependency>
            <groupId>me.collebol</groupId>
            <artifactId>ejgengine</artifactId>
            <version>v0.1.3-rc.1</version>
        </dependency>
    </dependencies>
    ```

3. **Reload Maven:**
    - Right-click `pom.xml` > Maven > Reload project

### Usage

Create a new Java class that extends `EJGEngine` and override the necessary methods:

```java
public class Main extends EJGEngine {

    public static void main(String[] args) {
        Main myGame = new Main();
        myGame.start();
    }

    @Override
    public void setup() {
        
    }

    @Override
    public void enable() {
        
    }

    @Override
    public void disable() {
        
    }
}
```
