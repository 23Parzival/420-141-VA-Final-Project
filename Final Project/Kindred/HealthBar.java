import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class HealthBar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HealthBar extends Actor {
    private Entity entity;        //the enemy it follows
    private int width;          //bar width
    private int height;         //bar height
    private boolean fixedPosition; //true for player HUD
    private int fixedX, fixedY;    //coordinates if fixed

    public HealthBar(Entity entity, int width, int height) {
        this.entity = entity;
        this.width = width;
        this.height = height;
        this.fixedPosition = false;
    }
    
    //constructor for player HUD (fixed on screen)
    public HealthBar(Entity entity, int width, int height, int x, int y) {
        this.entity = entity;
        this.width = width;
        this.height = height;
        this.fixedPosition = true;
        this.fixedX = x;
        this.fixedY = y;
    }

    public void act() {
        if (!fixedPosition && entity.getWorld() == null) {
            //entity no longer exists, remove the health bar too
            getWorld().removeObject(this);
            return;
        }
        draw();
        if (!fixedPosition) {
            //follow entity
            setLocation(entity.getX(), entity.getY() - entity.getImage().getHeight() / 2 - height);
        } else {
            //stay fixed
            setLocation(fixedX, fixedY);
        }
    }

    private void draw() {
        GreenfootImage bar = new GreenfootImage(width, height);
        //background
        bar.setColor(Color.RED);
        bar.fillRect(0, 0, width, height);
        //health portion
        bar.setColor(Color.GREEN);
        int healthWidth = (int)((double)entity.currentHealth / entity.maxHealth * width);
        bar.fillRect(0, 0, healthWidth, height);
        //border
        bar.setColor(Color.BLACK);
        bar.drawRect(0, 0, width - 1, height - 1);
        setImage(bar);
    }
}
