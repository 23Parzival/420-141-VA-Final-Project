import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class Entity here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Entity extends Actor
{
    protected int speed;
    protected int dx, dy;

    protected int maxHealth;
    protected int currentHealth;

    protected int attackCooldown;
    protected int attackTimer;
    
    protected Team team;
    public enum Team { PLAYER, ENEMY }
    

    public Entity(int speed, int maxHealth, int attackCooldown, int attackTimer) {
        this.speed = speed;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.attackCooldown = attackCooldown;
        this.attackTimer = attackTimer;
        
    }

    public void act() {
        updateMovement();
        moveWithCollision(dx, dy);
    }
    
    public Team getTeam() { return team; }

    protected void updateMovement() {}; //implemented in subclasses

    protected void moveWithCollision(int dx, int dy) {
        //no movement requested
        if (dx == 0 && dy == 0) {
            return;
        }
        //check for wall in the direction we want to move
        if(getOneObjectAtOffset(dx, dy, Wall.class) == null)
        {
            setLocation(getX() + dx, getY() + dy);
        }
    }

    public void takeDamage(int amount) {
        currentHealth -= amount;
        if (currentHealth <= 0) {
            currentHealth = 0;
            die();
        }
        if(this.getClass() == Player.class){
            SoundController.playGruntSound();
        }
        if(this.getClass() == Enemy.class){
            SoundController.playMonsterSound();
        }
        if(this.getClass() == Boss.class){
            SoundController.playMonsterSound();
        }
    }

    protected void die() {
        World w = getWorld();
        getWorld().removeObject(this);
        if(w.getObjects(Player.class).isEmpty()){
            Greenfoot.setWorld(new GameOver());
            SoundController.stopLevelMusic();
            SoundController.startLoseMusic();
        }
    }
    
    public int heal(int amount) {
        if (currentHealth >= maxHealth) return 0;
        
        int oldHealth = currentHealth;
        currentHealth += amount;
        if (currentHealth > maxHealth) currentHealth = maxHealth;
        return currentHealth - oldHealth;
    }
    
    protected Player getPlayer() {
        World w = getWorld();
        List<Player> players = w.getObjects(Player.class);

        if (!players.isEmpty()) {
            return players.get(0);   //return a Player, not List<Player>
        }

        return null; //if no player exists
    }
    
    protected void tryAttack() {
        //enemy attacking player
        if (this instanceof Enemy) {
            Player player = (Player)getOneIntersectingObject(Player.class);
            if (player != null && attackTimer <= 0) {
                performAttack();
                attackTimer = attackCooldown;
            }   
        }
    }
    
    protected void performAttack() {};
}
