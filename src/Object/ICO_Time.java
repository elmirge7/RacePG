package Object;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ICO_Time extends SuperObject {

    GamePanel gp;
    public ICO_Time(GamePanel gp) {
        this.gp = gp;
        name = "Time";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/time_icon.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
