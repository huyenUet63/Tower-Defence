import java.awt.*;

public abstract class GameEntity extends Rectangle {
    
    public GameEntity() {
   
    }
         
    public GameEntity(int x, int y, int width, int height) {
        setBounds(x, y, width, height);
    }
   
    //public abstract void physic();   
    public abstract void draw(Graphics g);
 
}

