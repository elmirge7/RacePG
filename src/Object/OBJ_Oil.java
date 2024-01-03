package Object;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Oil extends SuperObject {
    GamePanel gp;

    public OBJ_Oil(GamePanel gp) {
        this.gp = gp;
        name = "Oil";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/oil.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }

        collision = true;
    }
}
