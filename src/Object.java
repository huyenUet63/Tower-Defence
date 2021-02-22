
import java.awt.Graphics;

public class Object extends GameEntity {
    public int airID;
    
       
    public Object() {
        
    }
    
    public Object(int x, int y, int width, int height, int airID) {
        super(x, y, width, height);
        this.airID = airID;
    }
    
    @Override
    public void draw(Graphics g) {
        if (airID != Value1.air) {
            g.drawImage(GameField.set_air[airID], x, y, width, height, null);
        } 
        // ve object va target
    }
    
}
