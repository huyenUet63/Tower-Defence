import java.awt.event.*;
import java.awt.*;

public class KeyHandle implements MouseMotionListener, MouseListener {

    public void mouseClicked(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {
        GameField.store.click(e.getButton());
    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseDragged(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {
        GameField.mouse = new Point(e.getX()-(GameStage.size.width-GameField.myWidth)/2, e.getY()-(GameStage.size.height-GameField.myHeight)/2);
    }

}

