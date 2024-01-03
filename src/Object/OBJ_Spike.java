package Object;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Spike extends SuperObject {
    GamePanel gp;

    public OBJ_Spike(GamePanel gp) {
        this.gp = gp;
        name = "Spike";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/spike.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }

        collision = false;
    }
}
