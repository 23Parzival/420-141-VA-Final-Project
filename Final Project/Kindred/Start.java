import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Start here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Start extends Actor
{
    /**
     * Act - do whatever the Start wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public Start(){
        //shrink the image size
        GreenfootImage img = new GreenfootImage("playButtonGreen.png");
        img.scale(img.getWidth() / 3, img.getHeight() / 3);
        setImage(img);
        
    }
    
    public void act()
    {
        if(Greenfoot.mousePressed(this)){
            Greenfoot.setWorld(new Level1());
            SoundController.playButtonSound();
            SoundController.stopMenuMusic();
            SoundController.startLevelMusic();
        }
    }
}
