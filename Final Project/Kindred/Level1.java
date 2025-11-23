import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Level1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Level1 extends World
{
    /**
     * Constructor for objects of class Level1.
     * 
     */
    public Level1()
    {    
        super(1920, 1080, 1); 
        //create enemy
        Enemy enemy = new Enemy();
        addObject(enemy, 400, 300);

        //create health bar for enemy
        HealthBar hb = new HealthBar(enemy, enemy.getImage().getWidth(), 5);
        addObject(hb, enemy.getX(), enemy.getY() - enemy.getImage().getHeight() / 2 - 5);
        
        Player player = new Player();
        addObject(player, 100, 300); //anywhere in the world

        //add HUD health bar at top-left corner (x=60, y=15)
        HealthBar playerBar = new HealthBar(player, 400, 25, 220, 50);
        addObject(playerBar, 220, 50);
    }
}
