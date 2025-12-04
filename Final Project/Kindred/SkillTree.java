import greenfoot.*;

public class SkillTree extends Actor {
    private Player player;
    
    private int messageTimer = 0;
    private String currentMessage = "";
    
    private boolean key1Ready = true;
    private boolean key2Ready = true;
    
    public SkillTree(Player player) {
        this.player = player;
        updateImage();
    }

    public void act() {
        updateMessage(); 
        if (player != null && player.getWorld() != null) {
            checkSkillBuy(player);
        }
        else {
            return;
        }
    }

    private void updateImage() {
        GreenfootImage img = new GreenfootImage(175, 120);

        img.setColor(Color.WHITE);
        img.fill();

        img.setColor(Color.BLACK);
        img.drawRect(0, 0, 174, 119);

        img.drawString("SKILL TREE:", 10, 20);

        img.drawString("[1] Unlock Bow (30 XP)", 10, 44);
        img.drawString("[2] Instant Heal (20 XP)", 10, 64);
        img.drawString("[E] to use bow", 10, 84);
        img.drawString("[H] to use heal", 10, 104);

        setImage(img);
    }

    public void checkSkillBuy(Player player) {
        if (player == null) return;
        World w = getWorld();
        if (w == null) return;
        
        //buy Bow (key 1)
        if (Greenfoot.isKeyDown("1") && key1Ready) {
            key1Ready = false;
            if (player.getXP() < 30) {
                showMessage(w, "Not enough XP");
                SoundController.playErrorSound();
            } 
            else if (player.hasBow()) {
                showMessage(w, "Already unlocked!");
                SoundController.playErrorSound();
            }
            else {
                player.unlockBow();
                player.addXP(-30);
                showMessage(w, "Bow unlocked!");
                SoundController.playPerkSound();
            }
        }
        if (!Greenfoot.isKeyDown("1")) {
            key1Ready = true;
        }
        //buy Instant Heal ability (key 2)
        if (Greenfoot.isKeyDown("2") && key2Ready) {
            key2Ready = false;
            if (player.getXP() < 20) {
                showMessage(w, "Not enough XP");
                SoundController.playErrorSound();
            } 
            else if (player.hasInstantHeal()) {
                showMessage(w, "Already unlocked!");
                SoundController.playErrorSound();
            }
            else {
                player.unlockInstantHeal();
                player.addXP(-20);
                showMessage(w, "Instant Heal unlocked!");
                SoundController.playPerkSound();
            }
        }
        if (!Greenfoot.isKeyDown("2")) {
            key2Ready = true;
        }
    }
    
    private void showMessage(World w, String msg) {
        currentMessage = msg;
        messageTimer = 90; //1.5 seconds at 60fps
        w.showText(msg, 600, 650); //bottom center
    }
    
    private void updateMessage() {
        if (messageTimer > 0) {
            messageTimer--;
            if (messageTimer == 0 && getWorld() != null) {
                getWorld().showText("", 600, 650);
            }
        }
    }
}