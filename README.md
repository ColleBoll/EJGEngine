# EJGEngine

**EJGEngine** is an in-development 2D game engine for Java game development. While still in its development stages, the goal of EJGEngine is to become a user-friendly platform that enables anyone from beginners with minimal programming experience to seasoned developers to create 2D games quickly and efficiently.

***Build 2D games fast and efficient!***

## Features

- Cross-platform support (Windows, macOS and Linux)
- Render objects easily using build in renderers (based on OpenGL).
- User Interface components (Button's, Labels's etc.)
- Client event system.
- Game physics (Collisions etc.)
- Game simulations (Path finding etc.)
- Audio engine based on OpenAL.
- And a lot more!

## Preview



## Getting Started

### Prerequisites

- **Java 25** or higher: [Download from Oracle](https://www.oracle.com/java/technologies/downloads/)
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
            <version>0.2.7</version>
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
    public void register() {

    }

    @Override
    public void enable() {
        
    }

    @Override
    public void disable() {
        
    }
}
```
