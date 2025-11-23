import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Set;
import java.util.HashSet;

/**
 * Write a description of class HitBox here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Hitbox extends Actor {
    private int damage;
    private int duration; //frames it exists
    private Player player;
    private int offsetX, offsetY; //distance from player center
    
    //track enemies already hit
    private HashSet<Enemy> hitEnemies = new HashSet<>();
    
    public Hitbox(Player player, int width, int height, int damage, int duration, int offsetX, int offsetY) {
        this.player = player;
        this.damage = damage;
        this.duration = duration;
        this.offsetX = offsetX;
        this.offsetY = offsetY;

        GreenfootImage img = new GreenfootImage(width, height);
        img.setTransparency(0); //invisible
        //img.setColor(Color.RED); for debugging
        //img.fillRect(0, 0, width, height);
        setImage(img);
    }

    public void act() {
        //update position based on player's rotation
        double rad = Math.toRadians(player.getRotation());
        int dx = (int)Math.round(Math.cos(rad) * offsetX - Math.sin(rad) * offsetY);
        int dy = (int)Math.round(Math.sin(rad) * offsetX + Math.cos(rad) * offsetY);
        setLocation(player.getX() + dx, player.getY() + dy);
        setRotation(player.getRotation());
        
        //damage any enemies intersecting the hitbox
        for (Object obj : getIntersectingObjects(Enemy.class)) {
            Enemy enemy = (Enemy)obj;
            if (!hitEnemies.contains(enemy)) {
                enemy.takeDamage(damage);
                hitEnemies.add(enemy); //mark as already hit
            }
        }

        //decrease lifespan
        duration--;
        if (duration <= 0) {
            getWorld().removeObject(this);
        }
    }
}

