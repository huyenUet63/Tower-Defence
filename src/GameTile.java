import java.awt.*;

public class GameTile extends GameEntity {
    public int groundID;

    public GameTile() {
        
    } 

    public GameTile(int x, int y, int width, int height, int groundID) {
        super(x, y, width, height);
        this.groundID = groundID;
    }
 
    //@Override
    //public void physic() {
//        for (int y=0; y<block.length; y++) {
//            for (int x=0; x<block[0].length; x++) {
//                block[y][x].physic();
//            }
//        }
   // }
    
    @Override
    public void draw(Graphics g) {
        g.drawImage(GameField.set_ground[groundID], x, y, width, height, null);
    }

 }
