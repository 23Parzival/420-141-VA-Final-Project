import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Level1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Level1 extends World
{
    /**
     * Constructor for objects of class Level1.
     * 
     */
    public Level1()
    {    
        super(1200, 700, 1); 
        //create enemy
        Enemy enemy = new Enemy();
        addObject(enemy, 400, 300);

        Enemy enemy2 = new Enemy();
        addObject(enemy2, 500, 300);

        Enemy enemy3 = new Enemy();
        addObject(enemy3, 500, 400);
        
        Enemy enemy4 = new Enemy();
        addObject(enemy4,291,584);
        
        Enemy enemy5 = new Enemy();
        addObject(enemy5,640,84);

        //create health bar for enemy
        HealthBar hb = new HealthBar(enemy, enemy.getImage().getWidth(), 5);
        addObject(hb, enemy.getX(), enemy.getY() - enemy.getImage().getHeight() / 2 - 5);  

        HealthBar hb2 = new HealthBar(enemy2, enemy2.getImage().getWidth(), 5);
        addObject(hb2, enemy2.getX(), enemy2.getY() - enemy2.getImage().getHeight() / 2 - 5);

        HealthBar hb3 = new HealthBar(enemy3, enemy3.getImage().getWidth(), 5);
        addObject(hb3, enemy3.getX(), enemy3.getY() - enemy3.getImage().getHeight() / 2 - 5);
        
        HealthBar hb4 = new HealthBar(enemy4, enemy4.getImage().getWidth(), 5);
        addObject(hb4, enemy4.getX(), enemy4.getY() - enemy4.getImage().getHeight() / 2 - 5);

        HealthBar hb5 = new HealthBar(enemy5, enemy5.getImage().getWidth(), 5);
        addObject(hb5, enemy5.getX(), enemy5.getY() - enemy5.getImage().getHeight() / 2 - 5);

        Player player = new Player();
        addObject(player, 100, 300); //anywhere in the world

        //add HUD health bar at top-left corner (x=60, y=15)
        HealthBar playerBar = new HealthBar(player, 400, 25, 220, 50);
        addObject(playerBar, 220, 50);

        Boss boss = new Boss();
        addObject(boss, 1000, 350);

        HealthBar hb6 = new HealthBar(boss, boss.getImage().getWidth(), 5);
        addObject(hb6, boss.getX(), boss.getY() - boss.getImage().getHeight() / 2 - 5);

        addObject(new XpCounter(player), 550, 45);

        addObject(new SkillTree(player), 1200-85, 55);
        prepare();
    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Wall wall = new Wall();
        addObject(wall,560,572);
        Wall wall2 = new Wall();
        addObject(wall2,647,397);
        Wall wall3 = new Wall();
        addObject(wall3,643,220);
        Wall wall4 = new Wall();
        addObject(wall4,471,134);
    }
}
