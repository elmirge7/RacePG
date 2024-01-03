package Object;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Speed_Up_Down extends SuperObject {
    GamePanel gp;

    public OBJ_Speed_Up_Down(GamePanel gp) {
        this.gp = gp;
        name = "Speed Up";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/speed_up_down.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }

        collision = false;
    }
}
