package Object;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ICO_Heart_4 extends SuperObject {

    GamePanel gp;
    public ICO_Heart_4(GamePanel gp) {
        this.gp = gp;
        name = "Heart 4";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/heart_4_icon.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
