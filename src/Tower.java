import java.awt.geom.*;
import java.awt.*;
import java.util.ArrayList;

public class Tower extends Object{ //GameEntity {
    //public int airID;
    public int range;
    public Ellipse2D towerSquare;
    public int bulletSize = 13;
    public long lastShootTime;
    public double speed;
    public int strength;
    
    public int loseTime = 1, loseFrame = 0;
    
    public int shotEnemy = -1;
    public boolean shotting = false;
    public ArrayList<Bullet> bullet;
    
    public Tower() {
        
    }
    
    public Tower(int x, int y, int width, int height, int airID) {
        super(x, y, width, height, airID);
        //this.airID = airID;
        lastShootTime = System.currentTimeMillis();
        bullet = new ArrayList<>();
    }
          
    public void physic() {
        shotting = shotEnemy != -1 && towerSquare.intersects(GameField.enemy.get(shotEnemy));

        while (!bullet.isEmpty() && bullet.get(0).isArrived()) bullet.remove(0);
        if (lastShootTime + 1000 * speed < System.currentTimeMillis()) {
            lastShootTime = System.currentTimeMillis();
            if (airID <= Value1.airTowerLaser){
                for (int i=0; i<GameField.enemy.size(); i++) {
                    if (GameField.enemy.get(i).inGame){
                        if (towerSquare.intersects(GameField.enemy.get(i))) {   
                            shotting = true;
                            shotEnemy =i;
                            //System.out.println(i);
                            bullet.add(new Bullet(x+width/2, y+height/2, bulletSize, bulletSize, airID, GameField.enemy.get(shotEnemy), strength));
                            break;
                        }
                    }
                }
            }
        }
    }
    
    public int times=0, time = 12;
    public void update() {
        if (times >= time) {
            for (Bullet bullet1 : bullet) {
                if (bullet1 != null) {
                    bullet1.physic();
                }
            }
        times =0;
        }
        else times +=1;
    }
    
//    @Override
//    public void draw(Graphics g) {
//        if (airID != Value1.air) {
//            g.drawImage(GameField.set_air[airID], x, y, width, height, null);
//        } 
//        // ve tower va target
//    }
      
    public void fight(Graphics g) {
        if (GameField.isDebug) {
            if (airID <= Value1.airTowerLaser) {
                Graphics2D g2 = (Graphics2D) g;
//                g2.draw(new Ellipse2D.Double(x - towerSquareSize/2, y - towerSquareSize/2, width + towerSquareSize, height + towerSquareSize));
                g2.draw(towerSquare);
            }           
        }
    
        if (shotting) {
            for (Bullet bullet1 : bullet) {
                if (null != bullet1) {
                    bullet1.draw(g);
                }    
            }
        }
    }
    
    public void setSpeed(double speed) {
        this.speed = speed;
    }
    public double getSpeed(double speed) {
        return speed;
    }
    
    public void setStrength(int strength) {
        this.strength = strength;
    }
    public int getStrength(int strength) {
        return strength;
    }
    
    
    public void setRange(int range) {
        this.range = range;
    }
    public int getRange(){
        return range;
    }
    
    public void setTowerSquare(int range) {
        this.towerSquare = new Ellipse2D.Double(x - range/2, y - range/2, width + range, height + range);
    }
}
