import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Entity
{
    private int speed = 3;
    
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        move();
    }
    
    public void move() {
        int dx = 0;
        int dy = 0;

        if(Greenfoot.isKeyDown("w")) {
            dy -= speed;
        }
        else if(Greenfoot.isKeyDown("s")) {
            dy += speed;
        }
        else if(Greenfoot.isKeyDown("a")) {
            dx -= speed;  
        }
        else if(Greenfoot.isKeyDown("d")) {
            dx += speed;
        }
        // Only rotate if moving
        if (dx != 0 || dy != 0)
        {
            int angle = (int) Math.toDegrees(Math.atan2(dy, dx));
            setRotation(angle); 
        }
        moveIfClear(dx, dy);
    }
    
    private void moveIfClear(int dx, int dy)
    {
        // No movement requested
        if (dx == 0 && dy == 0) {
            return;
        }
        // Check for wall in the direction we want to move
        if(getOneObjectAtOffset(dx, dy, Wall.class) == null)
        {
            setLocation(getX() + dx, getY() + dy);
        }
    }
}
