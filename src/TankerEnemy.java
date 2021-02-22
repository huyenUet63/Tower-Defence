import java.awt.*;

public class TankerEnemy extends Enemy1 {
    public TankerEnemy() {
        super.setWalkSpeed(10);
    }
    
    @Override
    public void draw (Graphics g) {
        g.drawImage(GameField.set_enemy[1], x, y, width, height, null);
        
        //Health Bar
        g.setColor(new Color(180, 50, 50));
        g.fillRect(x, y - (bloodSpace+bloodHeight), width, bloodHeight);
        
        g.setColor(new Color(50, 180, 50));
        g.fillRect(x, y - (bloodSpace+bloodHeight), blood, bloodHeight);
        
        g.setColor(new Color(0, 0, 0));
        g.drawRect(x, y - (bloodSpace+bloodHeight), blood-1, bloodHeight-1);
    }
        
    @Override
    public void getDrop(/*int enemyID*/) {       
        GameField.coinage += 10;//Value1.enemyDrop[enemyID];
        //GameField.killed += 1;
    }
}
