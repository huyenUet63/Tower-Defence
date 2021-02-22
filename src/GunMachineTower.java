public class GunMachineTower extends Tower{
    public GunMachineTower(int x, int y, int width, int height) {
        super(x, y, width, height, 2);
        super.setSpeed(0.5);
        super.setStrength(5);
        super.setRange(80);
        super.setTowerSquare(80);
    }
}
