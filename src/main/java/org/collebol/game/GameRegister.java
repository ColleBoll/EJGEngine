package org.collebol.game;

import org.collebol.shared.objects.entity.Entity;
import org.collebol.shared.objects.entity.Player;
import org.collebol.game.world.World;
import org.collebol.shared.physics.PhysicsManager;
import org.collebol.shared.simulation.SimulationManager;

import java.util.*;

/**
 * The GameRegister class is responsible for managing players, entities, and worlds within the game.
 * It allows registration, retrieval, and removal of players, entities, and worlds.
 * It ensures that necessary validations are performed during these operations.
 *
 * @author ColleBol - <a href="mailto:contact@collebol.org">contact@collebol.org</a>
 * @since 1.0-dev
 */
public class GameRegister {

    private final Map<UUID, Player> players;
    private final Map<UUID, Entity> entitys;
    private final Map<String, World> worlds;

    private final PhysicsManager physicsManager;
    private final SimulationManager simulationManager;

    /**
     * Constructs a new GameRegister instance, initializing empty collections for players, entities, and worlds.
     */
    public GameRegister() {
        this.players = new HashMap<>();
        this.entitys = new HashMap<>();
        this.worlds = new HashMap<>();

        this.physicsManager = new PhysicsManager();
        this.simulationManager = new SimulationManager(this);
    }

    /**
     * Registers a player to the game register.
     *
     * @param player The player to be registered.
     * @throws RuntimeException if the player or player's UUID is null.
     */
    public void registerPlayer(Player player) {
        if (player == null) throw new RuntimeException("Player can not be null!");
        if (player.getUuid() == null) throw new RuntimeException("Please make sure to set the player UUID!");
        this.players.put(player.getUuid(), player);
        this.physicsManager.register(player);
    }

    /**
     * Retrieves a player from the game register based on their UUID.
     *
     * @param uuid The UUID of the player to retrieve.
     * @return The player associated with the given UUID.
     * @throws RuntimeException if the UUID is null or no player is found for the given UUID.
     */
    public Player getPlayer(UUID uuid) {
        if (uuid == null) throw new RuntimeException("UUID can not be null!");
        if (!this.players.containsKey(uuid)) throw new RuntimeException("UUID or Player not found!");
        return this.players.get(uuid);
    }

    public List<Player> getPlayers() {
        if (this.players.isEmpty()) return new ArrayList<>();
        return new ArrayList<>(this.players.values());
    }

    /**
     * Removes a player from the game register based on their UUID.
     *
     * @param uuid The UUID of the player to remove.
     * @return The player that was removed.
     * @throws RuntimeException if the UUID is null.
     */
    public Player removePlayer(UUID uuid) {
        if (uuid == null) throw new RuntimeException("UUID can not be null!");
        return this.players.remove(uuid);
    }

    /**
     * Registers an entity to the game register.
     *
     * @param entity The entity to be registered.
     * @throws RuntimeException if the entity is a player, or if the entity or its UUID is null.
     */
    public void registerEntity(Entity entity) {
        if (entity instanceof Player)
            throw new RuntimeException("To register a player use the registerPlayer() method!");
        if (entity == null || entity.getUuid() == null) throw new RuntimeException("Entity values can not be null!");
        this.entitys.put(entity.getUuid(), entity);
        this.physicsManager.register(entity);
    }

    /**
     * Retrieves an entity from the game register based on its UUID.
     *
     * @param uuid The UUID of the entity to retrieve.
     * @return The entity associated with the given UUID.
     * @throws RuntimeException if the UUID is null or no entity is found for the given UUID.
     */
    public Entity getEntity(UUID uuid) {
        if (uuid == null) throw new RuntimeException("UUID can not be null!");
        if (!this.players.containsKey(uuid)) throw new RuntimeException("UUID or Entity not found!");
        return this.players.get(uuid);
    }

    public List<Entity> getEntitys() {
        if (this.entitys.isEmpty()) return new ArrayList<>();
        return new ArrayList<>(this.entitys.values());
    }

    /**
     * Removes an entity from the game register based on its UUID.
     *
     * @param uuid The UUID of the entity to remove.
     * @return The entity that was removed.
     * @throws RuntimeException if the UUID is null.
     */
    public Entity removeEntity(UUID uuid) {
        if (uuid == null) throw new RuntimeException("UUID can not be null!");
        return this.entitys.remove(uuid);
    }

    /**
     * Registers a world to the game register.
     *
     * @param world The world to be registered.
     * @throws RuntimeException if the world or its name is null, or if the world is already registered.
     */
    public void registerWorld(World world) {
        if (world == null || world.getName() == null) throw new RuntimeException("World values can not be null!");
        if (this.worlds.containsKey(world.getName())) throw new RuntimeException("This world is already registered!");
        this.worlds.put(world.getName(), world);
    }

    /**
     * Retrieves a world from the game register based on its name.
     *
     * @param name The name of the world to retrieve.
     * @return The world associated with the given name.
     * @throws RuntimeException if the world name is null or no world is found for the given name.
     */
    public World getWorld(String name) {
        if (name == null) throw new RuntimeException("World name can not be null!");
        if (!this.worlds.containsKey(name)) throw new RuntimeException("World not found or does not exists!");
        return this.worlds.get(name);
    }

    /**
     * Removes a world from the game register based on its name.
     *
     * @param name The name of the world to remove.
     * @return The world that was removed.
     * @throws RuntimeException if the world name is null or no world is found for the given name.
     */
    public World removeWorld(String name) {
        if (name == null) throw new RuntimeException("World name can not be null!");
        if (!this.worlds.containsKey(name)) throw new RuntimeException("Removable world not found!");
        return this.worlds.remove(name);
    }

    public PhysicsManager getPhysicsManager() {
        return physicsManager;
    }

    public SimulationManager getSimulationManager() {
        return simulationManager;
    }
}
