import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SplashScreen extends World
{
    
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    private int timer = 300;
    
    public SplashScreen()
    {    
        super(1200, 700, 1,false); 
        setBackground(new GreenfootImage("VanierSplashScreen.png"));
    }
    
    public void act() 
    {
        timer--;
        if (timer <= 0) {
            Greenfoot.setWorld(new MainMenu());
            SoundController.startMenuMusic();
        }
    }
}
