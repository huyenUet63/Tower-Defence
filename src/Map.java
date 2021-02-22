import java.awt.*;
import java.util.ArrayList;

public class Map {
    public int worldWidth = 14;
    public int worldHeight = 8;
    public int blockSize = 52;

    public GameTile[][] block;
    //public Tower[][] tower;
    public Object[][] object;
    public ArrayList<Tower> tower1;

    public Map() {
        define();
    }

    public void define() {
        block = new GameTile[worldHeight][worldWidth];
        object = new Object[worldHeight][worldWidth];
        tower1 = new ArrayList<>();
                

        for (int y=0; y<block.length; y++) {
            for (int x=0; x<block[0].length; x++) {
                block[y][x] = new GameTile((GameField.myWidth/2)-(worldWidth*blockSize/2)+x*blockSize, y*blockSize, blockSize, blockSize, Value1.groundGrass/*, Value1.air*/);
                object[y][x] = new Object((GameField.myWidth/2)-(worldWidth*blockSize/2)+x*blockSize, y*blockSize, blockSize, blockSize, Value1.air);
            }
        }
    }

    public void physic() {
//        for (int y=0; y<block.length; y++) {
//            for (int x=0; x<block[0].length; x++) {
//                tower[y][x].physic();
//            }
//        }
//        
//        for (int y=0; y<block.length; y++) {
//            for (int x=0; x<block[0].length; x++) {
//                tower[y][x].update();
//            }
//        }
        
        for (int i=0; i< tower1.size(); i++) {
            tower1.get(i).physic();
        }
        for (int i=0; i< tower1.size(); i++) {
            tower1.get(i).update();
        }

    }

    public void draw(Graphics g) {
        for (int y=0; y<block.length; y++) {
            for (int x=0; x<block[0].length; x++) {
                block[y][x].draw(g);
                object[y][x].draw(g);
            }
        }
        //for (int i=0; i< tower1.size(); i++) {
        //    tower1.get(i).draw(g);
        //}
        for (int i=0; i< tower1.size(); i++) {
            tower1.get(i).fight(g);
        }
        
//        for (int y=0; y<block.length; y++) {
//            for (int x=0; x<block[0].length; x++) {
//                tower[y][x].fight(g);
//            }
//        }
    }
    
}
