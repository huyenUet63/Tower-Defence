import java.awt.Graphics;


public class Bullet extends GameEntity{
    public boolean arrived;
    public int id;
    public Enemy1 e;
    public int dame;
    
    public Bullet(int x, int y, int width, int height, int id, Enemy1 e, int dame) {
        super(x, y, width, height);
        arrived = false;
        this.id = id;
        this.e = e;
        this.dame = dame;
    }
    
    public void physic() {
        int targetX = e.x + e.width / 2;
        int targetY = e.y + e.height / 2;
        int distance = (int) Math.sqrt(Math.pow(targetX - x, 2) + Math.pow(targetY - y, 2));
        distance /= 8;

        if (distance != 0) {
            double moveX = 0, moveY = 0;
            moveX = (double) (targetX - x) / distance;
            moveY = (double) (targetY - y) / distance;
            this.x += (int) moveX;
            this.y += (int) moveY;
            
        } else {
            arrived = true;
            Sound.play(Sound.normalTowerSFX);
            e.loseBlood(dame);
            if (e.isDead()) {
                GameField.hasWon();
            } 
        }
    }
    
    public boolean isArrived() {
        return arrived;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(GameField.set_cell[id-2], x, y, width, height, null);
        System.out.println(x + " " + y);
    }
}
