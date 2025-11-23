import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class Entity here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Entity extends Actor
{
    protected int speed;
    protected int dx, dy;

    protected int maxHealth;
    protected int currentHealth;

    public Entity(int speed, int maxHealth) {
        this.speed = speed;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
    }

    public void act() {
        updateMovement();
        moveWithCollision(dx, dy);
    }

    protected void updateMovement() {}; //implemented in subclasses

    protected void moveWithCollision(int dx, int dy) {
        //no movement requested
        if (dx == 0 && dy == 0) {
            return;
        }
        //check for wall in the direction we want to move
        if(getOneObjectAtOffset(dx, dy, Wall.class) == null)
        {
            setLocation(getX() + dx, getY() + dy);
        }
    }

    public void takeDamage(int amount) {
        currentHealth -= amount;
        if (currentHealth <= 0) {
            currentHealth = 0;
            die();
        }
    }

    protected void die() {
        getWorld().removeObject(this);
    }
    
    protected Player getPlayer() {
        World w = getWorld();
        List<Player> players = w.getObjects(Player.class);

        if (!players.isEmpty()) {
            return players.get(0);   //return a Player, not List<Player>
        }

        return null; //if no player exists
    }
}
