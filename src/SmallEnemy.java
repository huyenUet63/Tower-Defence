import java.awt.*;

public class SmallEnemy extends Enemy1{
    public SmallEnemy() {
        super.setWalkSpeed(10);
    }
//    
//    public NormalEnemy(int x, int y, int width, int height) {
//        super(x, y, width, height, 1);
//        super.setWalkSpeed(30);
//    }
//    
    @Override
    public void draw (Graphics g) {
        g.drawImage(GameField.set_enemy[enemyID], x, y, width, height, null);
        
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
