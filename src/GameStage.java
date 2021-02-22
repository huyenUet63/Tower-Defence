import java.awt.*;
import javax.swing.*;


public class GameStage extends JFrame {
    public static String title = "Tower Defense";
    public static Dimension size = new Dimension(800, 600); // (width, height)

    public GameStage(){
        setTitle(title);
        setSize(size);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        init();
    }

    public void init() {
        setLayout(new GridLayout(1, 1, 0, 0));
        GameField field = new GameField(this);
        add(field);

        setVisible(true);
    }

}

