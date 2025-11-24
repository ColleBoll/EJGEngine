# EJGEngine

**EJGEngine** is an in-development 2D game engine for Java game development. While still in its development stages, the goal of EJGEngine is to become a user-friendly platform that enables anyone from beginners with minimal programming experience to seasoned developers to create 2D games quickly and efficiently.

***Build 2D games fast and efficient!***

## Features

- Cross-platform support (Windows, macOS and Linux).
- Render objects easily using build in renderers (based on OpenGL).
- User Interface components (Button's, Labels's etc.).
- Client event system.
- Game physics (Collisions etc.)
- Game simulations (Path finding etc.).
- Audio engine based on OpenAL.
- Extensive API Javadoc documentation.
- And a lot more!

# Preview
Here are some things you can do and use with this engine!

## Build in development details
![buildtools GIF](https://github.com/user-attachments/assets/4e8a33fd-75cb-4049-a365-83648ee9a653)
<br><br>

## World rendering
![Map rendering GIF](https://github.com/user-attachments/assets/87bfb2c1-ea0b-4cfb-90c3-b37222411659)
<details>
<summary>Show code</summary>

    @Override
    public Chunk generateChunk(Chunk chunk) {
        for (int i = 0; i < chunk.getChunkSize(); i++) {
            for (int j = 0; j < chunk.getChunkSize(); j++) {
                GameLocation location = new GameLocation(i + (chunk.getX() * chunk.getChunkSize()), j + (chunk.getY() * chunk.getChunkSize()));
                float chance = new Random().nextFloat();

                if (chance < 0.25f) {

                    GrassFlowerTile flowerTile = new GrassFlowerTile();
                    flowerTile.setGameLocation(location);
                    chunk.addTile(flowerTile);
                } else {

                    GrassTile tile = new GrassTile();
                    tile.setGameLocation(location);
                    chunk.addTile(tile);
                }
            }
        }
        this.world.getWorldFileManager().saveChunk(chunk);
        return chunk;
    }
    
</details>
<br>

## UI Components
![Button Hover GIF](https://github.com/user-attachments/assets/3865b0b2-1398-4d7c-bde0-ea71952dfb68)
<details>
<summary>Show code</summary>

    getEngine().getComponentHandler().registerNewComponent(new Button.ButtonBuilder(1)
            .backgroundColor(Color.BLUE)
            .width(400)
            .height(200)
                    .text(new Text.TextBuilder().text("Button").size(30).build())
            .position(new Vector2D(150, 120))
            .hoverEvent(((event, engine) -> {
                event.getButton().setBackgroundColor(event.isEnter() ?
                        Color.setOpacity(Color.BLUE, 0.5f) :
                        Color.BLUE
                );
            }))
            .build()
    );
    
</details>
<br>

## Simple client events
<img width="982" height="224" alt="Screenshot_20251124_135527" src="https://github.com/user-attachments/assets/34329aa8-5dd7-476d-b7d9-f863f8114d88" />

<br><br>

# Getting Started

## Prerequisites

- **Java 25** or higher: [Download from Oracle](https://www.oracle.com/java/technologies/downloads/)
- **Maven**: [Download from Apache](https://maven.apache.org/download.cgi)
- **IntelliJ IDEA** (recommended) or another IDE

## Installation
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
