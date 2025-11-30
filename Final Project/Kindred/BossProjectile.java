import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BossProjectile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BossProjectile extends Actor {
    private int speed = 8;
    private int damage = 2;
    private double dx, dy;
    private boolean initialized = false;

    public BossProjectile(int startX, int startY, int targetX, int targetY) {
        setLocation(startX, startY);
        double angle = Math.atan2(targetY - startY, targetX - startX);
        dx = Math.cos(angle) * speed;
        dy = Math.sin(angle) * speed;

        //set initial rotation to face target
        setRotation((int)Math.toDegrees(angle));
    }

    public void act() {
        if (!initialized && getWorld() != null) {
            //shooter.turnTowards(targetX, targetY);
            setLocation(getX() + (int)Math.round(dx), getY() + (int)Math.round(dy));
            initialized = true;
        }
        
        move(speed);
        checkCollisions();
    }

    private void checkCollisions() {
        Player p = (Player)getOneIntersectingObject(Player.class);
        if (p != null) {
            p.takeDamage(damage);
            if (getWorld() != null) getWorld().removeObject(this);
            return;
        }

        if (isAtEdge() || getOneIntersectingObject(Wall.class) != null) {
            getWorld().removeObject(this);
        }
    }
}

