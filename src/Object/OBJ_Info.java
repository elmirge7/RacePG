package Object;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Info extends SuperObject {
    GamePanel gp;

    public OBJ_Info(GamePanel gp) {
        this.gp = gp;
        name = "Info";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/info.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }

        collision = true;
    }
}
