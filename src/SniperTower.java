public class SniperTower extends Tower {
    public SniperTower(int x, int y, int width, int height) {
        super(x, y, width, height, 3);
        super.setSpeed(1.2);
        super.setStrength(10);
        super.setRange(150);
        super.setTowerSquare(150);
    }
}
