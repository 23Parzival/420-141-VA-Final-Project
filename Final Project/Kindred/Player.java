import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Entity
{
    private int swingCooldown = 0;      //frames left until next swing
    private final int swingCooldownMax = 20; //cooldown duration in frames

    public Player() {
        super(3, 10);
    }
    
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        updateMovement();
        handleSwing();
    }
    
    @Override
    protected void updateMovement() {
        //get current player position
        int px = getX();
        int py = getY();
        //get mouse info
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (mouse != null) {
            int mx = mouse.getX();
            int my = mouse.getY();

            //turn the player toward the mouse
            turnTowards(mx, my);
        }

        //move forward if W is pressed
        if (Greenfoot.isKeyDown("W")) {
            //compute dx, dy based on current rotation
            double rad = Math.toRadians(getRotation());
            int dx = (int)Math.round(Math.cos(rad) * speed);
            int dy = (int)Math.round(Math.sin(rad) * speed);
            
            moveWithCollision(dx, dy);
        }
        else if (Greenfoot.isKeyDown("S")) {
            double rad = Math.toRadians(getRotation());
            int dx = (int)Math.round(Math.cos(rad) * speed);
            int dy = (int)Math.round(Math.sin(rad) * speed);
            
            moveWithCollision(-dx, -dy);
        }
    }
    
    public void swingSword() {
        int damage = 1;
        int width = 60;  //horizontal reach of the sword
        int height = 20; //vertical reach of the sword
        int duration = 5; //lasts 5 frames
        int offsetX = 40; //distance in front of player
        int offsetY = 0;

        Hitbox hb = new Hitbox(this, width, height, damage, duration, offsetX, offsetY);
        getWorld().addObject(hb, getX(), getY());
    }
    
    private void handleSwing() {
        if (swingCooldown > 0) swingCooldown--;

        //press space to swing
        if (Greenfoot.isKeyDown("SPACE") && swingCooldown <= 0) {
            swingSword();
            swingCooldown = swingCooldownMax; //reset cooldown
        }
    }
}
