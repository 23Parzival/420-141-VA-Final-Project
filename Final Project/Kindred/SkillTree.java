import greenfoot.*;

public class SkillTree extends Actor {
    private Player player;
    
    public SkillTree(Player player) {
        this.player = player;
        updateImage();
    }

    public void act() {
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

        img.drawString("[1] Unlock Bow (30 XP)", 10, 50);
        img.drawString("[2] Instant Heal (20 XP)", 10, 80);

        setImage(img);
    }

    public void checkSkillBuy(Player player) {
        //buy Bow (key 1)
        if (Greenfoot.isKeyDown("1")) {
            if (player.hasBow()) {
                System.out.println("Already unlocked.");
            } 
            else if (player.getXP() < 30) {
                System.out.println("Not enough XP.");
            } 
            else {
                player.unlockBow();
                player.addXP(-30);
                System.out.println("Bow unlocked!");
            }
        }
        //buy Instant Heal ability (key 2)
        if (Greenfoot.isKeyDown("2")) {
            if (player.hasInstantHeal()) {
                System.out.println("Already unlocked.");
            } 
            else if (player.getXP() < 20) {
                System.out.println("Not enough XP.");
            } 
            else {
                player.unlockInstantHeal();
                player.addXP(-20);
                System.out.println("Instant Heal unlocked!");
            }
        }
    }
}