import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class Boss here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Boss extends Enemy
{
    private int phase = 1;
    
    private boolean awakened = false;
    
    private final int spawnX = 1000;
    private final int spawnY = 350;
    private boolean reachedSpawn = false;
    
    private int rangedCooldown = 0;
    private int rangedCooldownTime = 45;

    private GreenfootImage bossIdle = new GreenfootImage("GoblinBoss.png");
    private GreenfootImage bossAttack = new GreenfootImage("GoblinBossAttack.png");
    private GreenfootImage bossAngry = new GreenfootImage("GoblinBossAngry.png");
    private GreenfootImage bossAngryAttack = new GreenfootImage("GoblinBossAngryAttack.png");
    

    public Boss() {
        super();

        //override Enemys base stats
        this.maxHealth = 15;
        this.currentHealth = maxHealth;

        this.attackCooldown = 40;   //slower but stronger attacks
        this.detectionRange = 1200;  //larger aggro range
        this.speed = 1;             //faster than normal goblins
        this.team = Team.ENEMY;

        setImage(bossIdle);
        setRotation(180);
        
    }
    
    @Override 
    public void act() {
        if (!awakened) return;
        updatePhase();

        if (phase == 3) {
            handlePhaseThree();
        } else {
            super.act();
        }

        if (rangedCooldown > 0) rangedCooldown--;
    }
    
    private void updatePhase() {
        if (!awakened) return;
        if (currentHealth <= 5 && phase < 3) {
            phase = 3;
            reachedSpawn = false;
        }
        else if (currentHealth <= 10 && phase < 2) {
            phase = 2;
            speed = 2;
            detectionRange = 500;
        }
    }
    
    private void handlePhaseThree() {
        //stop wandering completely
        dx = 0;
        dy = 0;

        //retreat first
        if (!reachedSpawn) {
            retreatToSpawn();
            return;
        }

        Player player = getPlayer();
        if (player != null) {
            turnTowards(player.getX(), player.getY());
            rangedAttack();
        }
    }
    
    private void retreatToSpawn() {
        int ex = getX();
        int ey = getY();

        int dist = (int)Math.hypot(spawnX - ex, spawnY - ey);

        if (dist < 5) {
            reachedSpawn = true;
            return;
        }

        //move toward spawn
        double angle = Math.atan2(spawnY - ey, spawnX - ex);
        int dx = (int)Math.round(Math.cos(angle) * speed);
        int dy = (int)Math.round(Math.sin(angle) * speed);

        turnTowards(spawnX, spawnY);
        moveWithCollision(dx, dy);
    }
    
    private void rangedAttack() {
        if (rangedCooldown > 0) return;

        Player player = getPlayer();
        if (player == null) return;

        World w = getWorld();
        if (w == null) return;

        //shoot at player
        BossProjectile p = new BossProjectile(getX(), getY(), player.getX(), player.getY());
        w.addObject(p, getX(), getY());

        rangedCooldown = rangedCooldownTime;
    }

    @Override
    protected void updateMovement() {
        if (phase == 3) {
            return;
        }
        
        Player player = getPlayer();
        if (player == null) return;

        int px = player.getX();
        int py = player.getY();
        int ex = getX();
        int ey = getY();
        int dist = (int)Math.hypot(px - ex, py - ey);

        //chase hard if in range, wander otherwise
        if (dist < detectionRange) {
            double angle = Math.atan2(py - ey, px - ex);
            int dx = (int)Math.round(Math.cos(angle) * speed);
            int dy = (int)Math.round(Math.sin(angle) * speed);
            turnTowards(px, py);
            moveWithCollision(dx, dy);
        }
    }

    @Override
    protected void performAttack() {
        World w = getWorld();
        if (w == null) return;

        if(phase >= 2) setImage(bossAngryAttack);
        else setImage(bossAttack);
        swingAnimTime = maxSwingAnimTime;
        //Greenfoot.playSound(""); boss sound here

        //boss hitbox deals more damage + larger area
        Hitbox h = new Hitbox(this, 40, 40, 1, 15, 25, 0);
        w.addObject(h, getX(), getY());
    }
    
    @Override
    public void takeDamage(int amount) {
        if (getWorld() == null) return;
        //cant take damage while he returns to spawn
        if (phase == 3 && !reachedSpawn) return;
        if (!awakened) {
            awakened = true;
            World w = getWorld();
            if (w != null) {
                w.removeObjects(w.getObjects(Wall.class));  // remove all walls immediately
            }
        }
        super.takeDamage(amount);
    }

    @Override
    protected void die() {
        World w = getWorld();
        if (w != null) {
            Player player = getPlayer();
            if (player != null) {
                player.addXP(50); //boss gives more XP
            }
        }
        getWorld().removeObject(this);
    }
    
    @Override
    protected void updateSwingAnimation() {
        if (swingAnimTime > 0) {
            swingAnimTime--;
            if (swingAnimTime == 0) {
                if (phase >= 2) {
                    setImage(bossAngry);
                } 
                else {
                    setImage(bossIdle);
                } 
            }
        }
    }
}

