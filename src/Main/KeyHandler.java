package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public boolean upLeftPressed, upRightPressed, downRightPressed, downLeftPressed;

    // DEBUG
    boolean checkDrawTime = false;
    @Override
    public void keyTyped(KeyEvent e) {
        // Non utilisé
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_Z) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_Q) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        // Gestion des diagonales
        if (code == KeyEvent.VK_Z && leftPressed) {
            upLeftPressed = true;
        }
        if (code == KeyEvent.VK_Z && rightPressed) {
            upRightPressed = true;
        }
        if (code == KeyEvent.VK_S && rightPressed) {
            downRightPressed = true;
        }
        if (code == KeyEvent.VK_S && leftPressed) {
            downLeftPressed = true;
        }

        // DEBUG
        if (code == KeyEvent.VK_T) {
            if (checkDrawTime == false) {
                checkDrawTime = true;
            } else if (checkDrawTime == true){
                checkDrawTime = false;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_Z || code == KeyEvent.VK_A || code == KeyEvent.VK_E) {
            upPressed = false;
            upLeftPressed = false;
            upRightPressed = false;
        }
        if (code == KeyEvent.VK_Q) {
            leftPressed = false;
            upLeftPressed = false;
            downLeftPressed = false;
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_W || code == KeyEvent.VK_X) {
            downPressed = false;
            downRightPressed = false;
            downLeftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
            upRightPressed = false;
            downRightPressed = false;
        }
    }
}
