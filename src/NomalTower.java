public class NomalTower extends Tower{
    public NomalTower(int x, int y, int width, int height) {
        super(x, y, width, height, 2);
        super.setSpeed(0.75);
        super.setStrength(8);
        super.setRange(100);
        super.setTowerSquare(100);
    }
}
