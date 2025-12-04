import greenfoot.*;

/**
 * Write a description of class SoundController here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SoundController
{
    private static GreenfootSound menuMusic = new GreenfootSound("loop.wav");
    private static GreenfootSound button = new GreenfootSound("vgmenuselect.wav");
    private static GreenfootSound sword = new GreenfootSound("combat-sword-swing-hit.mp3");
    private static GreenfootSound grunt = new GreenfootSound("cut_grunt2.wav");
    private static GreenfootSound monster = new GreenfootSound("monster-1.wav");
    private static GreenfootSound death = new GreenfootSound("monster-6.wav");
    private static GreenfootSound levelMusic = new GreenfootSound("C64 Level 1.mp3");
    private static GreenfootSound heal = new GreenfootSound("blessing2.wav");
    private static GreenfootSound shoot = new GreenfootSound("Archers-shooting.wav");
    private static GreenfootSound unlockPerk = new GreenfootSound("Menu Selection Click.wav");
    private static GreenfootSound gameWinMusic = new GreenfootSound("synth_3.wav");
    private static GreenfootSound gameOverMusic = new GreenfootSound("laidback.wav");
    private static GreenfootSound error = new GreenfootSound("error.wav");

    public static void startMenuMusic() {
        menuMusic.playLoop();
    }

    public static void stopMenuMusic() {
        menuMusic.stop();
    }
    
    public static void startLevelMusic() {
        levelMusic.playLoop();
    }

    public static void stopLevelMusic() {
        levelMusic.stop();
    }
    
    public static void startWinMusic() {
        gameWinMusic.playLoop();
    }

    public static void stopWinMusic() {
        gameWinMusic.stop();
    }
    
    public static void startLoseMusic() {
        gameOverMusic.playLoop();
    }

    public static void stopLoseMusic() {
        gameOverMusic.stop();
    }

    public static void playButtonSound() {
        button.play();
    }
    
    public static void playSwordSound() {
        sword.play();
    }
    
    public static void playGruntSound() {
        grunt.play();
    }
    
    public static void playMonsterSound() {
        monster.play();
    }
    
    public static void playDeathSound() {
        death.play();
    }

    public static void playHealSound() {
        heal.play();
    }
    
    public static void playShootSound() {
        shoot.play();
    }
    
    public static void playPerkSound() {
        unlockPerk.play();
    }
    
    public static void playErrorSound() {
        error.play();
    }
}
