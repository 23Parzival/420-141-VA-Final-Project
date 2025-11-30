import greenfoot.*;

public class Arrow extends Actor {

    private int speed = 5;
    private int damage = 1;

    public Arrow(int rotation) {
        setRotation(rotation);
    }

    public void act() {
        move(speed);
        if (checkHitEnemy()) return;
        if (checkEdge()) return;
        if (checkHitWall()) return;
    }

    private boolean checkHitEnemy() {
        Enemy enemy = (Enemy) getOneIntersectingObject(Enemy.class);

        if (enemy != null) {
            enemy.takeDamage(damage);
            getWorld().removeObject(this);
            return true; 
        }
        return false;
    }
    
    private boolean checkHitWall() {
        Wall w = (Wall)getOneIntersectingObject(Wall.class);
        if (w != null) {
            getWorld().removeObject(this);
            return true;
        }
        return false;
    }

    private boolean checkEdge() {
        if (isAtEdge()) {
            getWorld().removeObject(this);
            return true; 
        }
        return false;
    }
}

