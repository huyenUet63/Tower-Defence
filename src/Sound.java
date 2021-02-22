
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Sound {

    private static Clip clip;
    public static final File BGSFX = new File("res/SFX/BG.wav");
    public static final File inGameSFX = new File("res/SFX/welcome.wav");
    public static final File gameOverSFX = new File("res/SFX/gameOver.wav");
    public static final File normalTowerSFX = new File("res/SFX/normal.wav");
    public static final File machineTowerSFX = new File("res/SFX/machine.wav");
    public static final File sniperTowerSFX = new File("res/SFX/sniper.wav");
    public static final File dieSFX = new File("res/SFX/dieEnemy.wav");
    public static final File enemyAttackSFX = new File("res/SFX/addEnemy.wav");

    public static void play(File f) {
        try {
            AudioInputStream stream = AudioSystem.getAudioInputStream(f);
            clip = AudioSystem.getClip();
            clip.open(stream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void stop() {
        clip.stop();
    }

}
