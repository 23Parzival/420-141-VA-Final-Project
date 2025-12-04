import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class XpCounter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class XpCounter extends Actor {
    private Player player;

    public XpCounter(Player player) {
        this.player = player;
        updateImage();
    }

    public void act() {
        if (player != null && player.getWorld() != null) {
            updateImage();
        } 
        else {
            return;
        }
    }

    private void updateImage() {
        GreenfootImage img = new GreenfootImage(200, 25);
        img.setColor(new Color(0,0,0,0));
        img.fill();

        img.setColor(Color.WHITE);
        img.setFont(new Font("Arial", 20));
        img.drawString("XP: " + player.getXP(), 5, 25);

        setImage(img);
    }
}

