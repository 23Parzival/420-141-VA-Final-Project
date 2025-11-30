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
    private Entity owner;
    private int offsetX, offsetY; //distance from player center
    
    //track enemies already hit
    private HashSet<Entity> hitEntities = new HashSet<>();
    
    public Hitbox(Entity owner, int width, int height, int damage, int duration, int offsetX, int offsetY) {
        this.owner = owner;
        this.damage = damage;
        this.duration = duration;
        this.offsetX = offsetX;
        this.offsetY = offsetY;

        GreenfootImage img = new GreenfootImage(width, height);
        img.setTransparency(0); //invisible
        //img.setColor(Color.RED); //for debugging
        //img.fillRect(0, 0, width, height);
        setImage(img);
    }

    public void act() {
        if (owner == null || owner.getWorld() == null) {
            if (getWorld() != null) {
                getWorld().removeObject(this);
                return;
            }
        }
        updatePosition();
        damageTargets();
        updateLifetime();
    }
    
    private void updatePosition() {
        if (owner == null || owner.getWorld() == null) return;
        //update position based on player's rotation
        double rad = Math.toRadians(owner.getRotation());
        int dx = (int)Math.round(Math.cos(rad) * offsetX - Math.sin(rad) * offsetY);
        int dy = (int)Math.round(Math.sin(rad) * offsetX + Math.cos(rad) * offsetY);
        if (getWorld() != null) setLocation(owner.getX() + dx, owner.getY() + dy);
        setRotation(owner.getRotation());
    }
    
    private void damageTargets() {
        for (Entity e : getIntersectingObjects(Entity.class)) {
            if (e == owner) continue;
            if (e.getTeam() == owner.getTeam()) continue;
            if (hitEntities.contains(e)) continue;

            e.takeDamage(damage);
            hitEntities.add(e);
        }
    }

    private void updateLifetime() {
        duration--;
        if (duration <= 0 && getWorld() != null) {
            getWorld().removeObject(this);
        }
    }
}

