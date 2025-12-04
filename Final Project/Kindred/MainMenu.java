import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MainMenu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MainMenu extends World
{
    /**
     * Constructor for objects of class MainMenu.
     * 
     */
    
    
    public MainMenu()
    {    
        super(1200, 700, 1); 
        prepare();
        
    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Start start = new Start();
        addObject(start,605,490);
        start.setLocation(616,485);
        start.setLocation(614,485);
        logo logo = new logo();
        addObject(logo,686,358);
        logo.setLocation(603,221);
        start.setLocation(619,550);
        logo.setLocation(640,340);
        logo.setLocation(677,282);
        logo.setLocation(625,276);
        logo.setLocation(603,281);
        logo.setLocation(615,273);
        logo.setLocation(624,267);
    }
}
