import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameOver here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameOver extends World
{
    /**
     * Constructor for objects of class GameOver.
     * 
     */
    public GameOver()
    {    
        super(1200, 700, 1);
        GreenfootImage bg = new GreenfootImage("youlose.jpg");
        bg.scale(getWidth(), getHeight());
        setBackground(bg);
    }
}
