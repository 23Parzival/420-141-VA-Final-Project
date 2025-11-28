import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Entity
{
    private int xp = 0;
    private boolean canFire = true;
    
    //skills
    private boolean hasBow = false;
    private boolean hasInstantHeal = false;
    
    private int healCooldown = 0;
    private final int healCooldownMax = 280; 
    
    public Player() {
        super(3, 10, 60, 0);
        team = Team.PLAYER;
    }
    
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        updateMovement();
        
        if (attackTimer > 0) attackTimer--;
        swingSword();
        
        if (healCooldown > 0) {
            healCooldown--;
        }
        handleHeal();
    }
    
    public void addXP(int amount) {
        xp += amount;
    }

    public int getXP() {
        return xp;
    }
    
    public boolean hasBow() { 
        return hasBow; 
    }

    public void unlockBow() {
        hasBow = true;
    }
    
    public boolean hasInstantHeal() { 
        return hasInstantHeal; 
    }
    
    public void unlockInstantHeal() {
        hasInstantHeal = true;
    }
    
    public int getHealCooldown() {
        return healCooldown;
    }
    
    private void handleHeal() {
        if (hasInstantHeal() && Greenfoot.isKeyDown("H") && healCooldown <= 0) {

            heal(3);  //heal the player
            System.out.println("Instant heal used!");

            healCooldown = healCooldownMax; //reset cooldown
        }
    }
    
    @Override
    protected void updateMovement() {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (mouse != null) {
            int mx = mouse.getX();
            int my = mouse.getY();
            turnTowards(mx, my);
        }
        int dx = 0;
        int dy = 0;

        if (Greenfoot.isKeyDown("W")) dy -= speed;
        if (Greenfoot.isKeyDown("S")) dy += speed;
        if (Greenfoot.isKeyDown("A")) dx -= speed;
        if (Greenfoot.isKeyDown("D")) dx += speed;

        //normalize diagonal movement (so diagonals aren't faster)
        if (dx != 0 && dy != 0) {
            dx = (int)(dx * 0.7071);
            dy = (int)(dy * 0.7071);
        }

        moveWithCollision(dx, dy);
    }
    
    public void swingSword() {
        //press space to swing
        if(Greenfoot.isKeyDown("space") && attackTimer <= 0 && canFire) {
            performAttack();
            attackTimer = attackCooldown; //reset cooldown
            canFire = false;
        }
        if(!Greenfoot.isKeyDown("space")) canFire = true;
    }

    @Override
    public void performAttack() {
        World w = getWorld();
        if(w == null) return;
        
        Hitbox hb = new Hitbox(this, 60, 20, 1, 5, 40, 0);
        getWorld().addObject(hb, getX(), getY());
    }
}
