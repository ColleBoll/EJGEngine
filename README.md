# EJGEngine

**EJGEngine** is an ambitious, in-development game engine written entirely in Java. While still in its early stages, the goal of EJGEngine is to become a powerful and user-friendly platform that enables anyone — from beginners with minimal programming experience to seasoned developers — to create 2D games quickly and efficiently.

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

1. **Add the EJGEngine dependency to your `pom.xml`:**
    ```xml
    <dependencies>
        <dependency>
            <groupId>org.collebol</groupId>
            <artifactId>EJGEngine</artifactId>
            <version>v0.1.4</version>
        </dependency>
    </dependencies>
    ```

2. **Reload Maven:**
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
