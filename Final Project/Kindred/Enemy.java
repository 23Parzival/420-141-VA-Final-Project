import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Entity
{
    protected int detectionRange = 200;   //distance in pixels
    protected int wanderRotation = 0; //current wandering direction (degrees)
    protected int wanderTimer = 0;    //frames left before changing direction
    
    private GreenfootImage idleImage = new GreenfootImage("Goblin.png");
    private GreenfootImage swingImage = new GreenfootImage("GoblinNoSword.png");

    protected int swingAnimTime = 0;
    protected int maxSwingAnimTime = 15;
    
    
    public Enemy() {
        super(1, 3, 60, 0);
        team = Team.ENEMY;
        setImage("GoblinNoSword.png");
        setRotation(Greenfoot.getRandomNumber(360));
        
    }
    
    /**
     * Act - do whatever the Enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        updateMovement();
        if (attackTimer > 0) attackTimer--;
        tryAttack();
        updateSwingAnimation();
    }
    
    @Override
    protected void updateMovement() {
        //reset movement
        dx = 0;
        dy = 0;

        //get the player from the world
        Player player = getPlayer();

        if (player == null) return;

        //calculate the distance to the player
        int px = player.getX();
        int py = player.getY();

        int ex = getX();
        int ey = getY();

        int dist = (int)Math.hypot(px - ex, py - ey);

        //only chase if player within detection range
        if (dist < detectionRange && dist > 0) {
            //compute dx/dy based on direction vector
            double angle = Math.atan2(py - ey, px - ex);
            
            dx = (int)Math.round(Math.cos(angle) * speed);
            dy = (int)Math.round(Math.sin(angle) * speed);
            turnTowards(px, py);
            moveWithCollision(dx, dy);
        }
        else {
            handleWandering();
        }
    }
    
    protected void handleWandering() {
        wanderTimer--;

        //pick a new random direction if timer expired
        if (wanderTimer <= 0) {
            wanderRotation = (int)(Math.random() * 360); //new random rotation
            wanderTimer = 30 + (int)(Math.random() * 60); //wander 30–90 frames
        }

        //face wandering direction
        setRotation(wanderRotation);

        //move in that direction if no wall ahead
        double rad = Math.toRadians(wanderRotation);
        int dx = (int)Math.round(Math.cos(rad) * speed);
        int dy = (int)Math.round(Math.sin(rad) * speed);

        if (getOneObjectAtOffset(dx, dy, Wall.class) == null) {
            moveWithCollision(dx, dy);
        } else {
            //if blocked, force pick a new direction next frame
            wanderTimer = 0;
        }
    }
    
    protected void updateSwingAnimation() {
        if (swingAnimTime > 0) {
            swingAnimTime--;
            if (swingAnimTime == 0) {
                setImage(idleImage); //reset back to idle
            }
        }
    }
    
    @Override
    protected void performAttack() {
        World w = getWorld();
        if (w == null) return;
        
        //Greenfoot.playSound(""); enemy sound here
        setImage(swingImage);
        swingAnimTime = maxSwingAnimTime;

        Hitbox h = new Hitbox(this, 20, 20, 1, 5, 25, 0);
        w.addObject(h, getX(), getY());
    }
    
    @Override
    protected void die() {
        //give XP to the player
        World w = getWorld();
        if (w != null) {
            Player player = (Player) w.getObjects(Player.class).get(0); // single player
            if (player != null) {
                player.addXP(10);
            }
            //remove this enemy
            w.removeObject(this);
            SoundController.playDeathSound();
        }
    }
}
