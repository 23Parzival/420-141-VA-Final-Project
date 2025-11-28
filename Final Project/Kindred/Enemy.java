import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Entity
{
    private int detectionRange = 200;   //distance in pixels
    private int wanderRotation = 0; //current wandering direction (degrees)
    private int wanderTimer = 0;    //frames left before changing direction  
    
    public Enemy() {
        super(1, 5, 60, 0);
        team = Team.ENEMY;
        setImage("Goblin.png");
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
            
            int dx = (int)Math.round(Math.cos(angle) * speed);
            int dy = (int)Math.round(Math.sin(angle) * speed);
            turnTowards(px, py);
            moveWithCollision(dx, dy);
        }
        else {
            handleWandering();
        }
    }
    
    private void handleWandering() {
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
    
    @Override
    protected void performAttack() {
        World w = getWorld();
        if (w == null) return;

        Hitbox h = new Hitbox(this, 30, 20, 1, 5, 25, 0);
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
        }
    }
}
