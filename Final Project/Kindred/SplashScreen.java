import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SplashScreen extends World
{
    World w = new MainMenu();
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public SplashScreen()
    {    
        super(1200, 700, 1,false); 
        setBackground(new GreenfootImage("VanierSplashScreen.png"));
    }
    
    public void act() {
        Greenfoot.delay(300);
        Greenfoot.setWorld(w);
    }
}
