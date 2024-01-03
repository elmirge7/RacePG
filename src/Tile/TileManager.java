package Tile;

import Main.GamePanel;
import Main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[50];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("/maps/racepg_map_01_2.txt");
    }

    public void getTileImage() {
        setup(0, "dirt", false, false);
        setup(1, "grass", false, true);
        setup(2, "grass_alt", false, true);
        setup(3, "grass_left_direction", true, false);
        setup(4, "road", false, false);
        setup(5, "road_bottom", false, false);
        setup(6, "road_bottom_left", false, false);
        setup(7, "road_bottom_right", false, false);
        setup(8, "road_horizontal", false, false);
        setup(9, "road_left", false, false);
        setup(10, "road_right", false, false);
        setup(11, "road_top", false, false);
        setup(12, "road_top_left", false, false);
        setup(13, "road_top_right", false, false);
        setup(14, "road_vertical", false, false);
        setup(15, "water", true, false);
        setup(16, "water_alt", true, false);
        setup(17, "water_bottom", true, false);
        setup(18, "water_bottom_left", true, false);
        setup(19, "water_bottom_right", true, false);
        setup(20, "water_left", true, false);
        setup(21, "water_right", true, false);
        setup(22, "water_top", true, false);
        setup(23, "water_top_left", true, false);
        setup(24, "water_top_right", true, false);
    }

    public void setup(int index, String imageName, boolean collision, boolean isGrass){

        UtilityTool uTool = new UtilityTool();
        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/"+ imageName +".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
            tile[index].isGrass = isGrass;
        }catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();

                while (col < gp.maxWorldCol) {
                    String[] numbers = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch (Exception ignored) {

        }
    }

    public void draw(Graphics2D g2) {

        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
               worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
               worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
               worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                g2.drawImage(tile[tileNum].image, screenX, screenY, null);
            }

            worldCol++;

            if(worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }

    }
}
