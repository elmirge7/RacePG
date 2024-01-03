package Object;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Finish extends SuperObject {
    GamePanel gp;

    public OBJ_Finish(GamePanel gp) {
        this.gp = gp;
        name = "Finish";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/finish.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }

        collision = false;
    }
}
