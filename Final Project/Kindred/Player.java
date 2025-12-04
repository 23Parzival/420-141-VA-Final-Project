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
    private boolean canShootBow = true;
    //skills
    private boolean hasBow = false;
    private boolean hasInstantHeal = false;
    
    private int healCooldown = 0;
    private final int healCooldownMax = 400; 
    
    private int bowCooldown = 0;
    private final int bowCooldownTime = 300;
    
    private GreenfootImage idleImage = new GreenfootImage("GingerKnightBiggerNoSword.png");
    private GreenfootImage swingImage = new GreenfootImage("GingerKnightBigger.png");

    private int swingAnimTime = 0;
    private int maxSwingAnimTime = 10;
    
    
    public Player() {
        super(3, 10, 60, 0);
        team = Team.PLAYER;
        setImage("GingerKnightBiggerNoSword.png");
        
    }
    
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        updateMovement();
        
        shootBow();
        swingSword();
        updateSwingAnimation();
        
        if (attackTimer > 0) attackTimer--;
        if (bowCooldown > 0) bowCooldown--;
        if (healCooldown > 0) healCooldown--;
        handleHeal();
        checkForWin();
    }
    
    public void checkForWin(){
        World w = getWorld();
        if(w.getObjects(Enemy.class).isEmpty() || w.getObjects(Boss.class).isEmpty()){
            Greenfoot.setWorld(new WinScreen());
            SoundController.stopLevelMusic();
            SoundController.startWinMusic();
        }
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
        World w = getWorld();
        if (w == null) return;
        
        if (healCooldown > 0) {
            w.showText("Heal Cooldown: " + healCooldown, 100, 100);
            healCooldown--;
            return;
        } 
        else {
            //no cooldown, clear the text
            w.showText("", 100, 100);
        }
        if (hasInstantHeal() && Greenfoot.isKeyDown("H") && healCooldown <= 0) {

            heal(1);  //heal the player
            w.showText("Instant Heal used!", 100, 100);
            SoundController.playHealSound();

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
    
    private void updateSwingAnimation() {
        if (swingAnimTime > 0) {
            swingAnimTime--;
            if (swingAnimTime == 0) {
                setImage(idleImage); //reset back to idle
            }
        }
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
        
        SoundController.playSwordSound();
        setImage(swingImage);
        swingAnimTime = maxSwingAnimTime;
        
        Hitbox hb = new Hitbox(this, 60, 20, 1, 5, 40, 0);
        getWorld().addObject(hb, getX(), getY());
    }
    
    public void shootBow() {
        World w = getWorld();
        if (w == null) return;
        if (bowCooldown > 0) {
            w.showText("Bow Cooldown: " + bowCooldown, 100, 150); 
            bowCooldown--;
            return;
        }
        else {
            w.showText("", 100, 150);
        }
        if (Greenfoot.isKeyDown("E") && hasBow && bowCooldown <= 0 && canShootBow) {
            shootArrow();
            SoundController.playShootSound();
            bowCooldown = bowCooldownTime;
            canShootBow = false;
        }
        if (!Greenfoot.isKeyDown("E")) canShootBow = true;
    }
    
    private void shootArrow() {
        Arrow arrow = new Arrow(getRotation());
        getWorld().addObject(arrow, getX(), getY());
    }
}
