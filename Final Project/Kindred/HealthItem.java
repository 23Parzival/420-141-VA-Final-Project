import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class HealthItem here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HealthItem extends Actor
{
    private int healAmount = 2;
    /**
     * Act - do whatever the HealthItem wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        //check if player intersects
        setLocation(getX(), getY() + (int)(Math.sin(System.currentTimeMillis() / 200.0) * 2));
        Player player = (Player)getOneIntersectingObject(Player.class);
        if (player != null) {
            int healed = player.heal(healAmount);
            if(healed > 0) {
                getWorld().removeObject(this);
            }
        }
    }
    }
