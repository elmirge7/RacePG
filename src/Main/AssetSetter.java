package Main;

import Object.OBJ_Key;
import Object.OBJ_Door;
import Object.OBJ_Info;
import Object.OBJ_Spike;
import Object.OBJ_Oil;
import Object.OBJ_Finish;
import Object.OBJ_Speed_Up;
import Object.OBJ_Speed_Up_Down;
import Object.OBJ_Coin;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        gp.obj[0] = new OBJ_Info(gp);
        gp.obj[0].worldX = 44 * gp.tileSize;
        gp.obj[0].worldY = 41 * gp.tileSize;

        gp.obj[1] = new OBJ_Spike(gp);
        gp.obj[1].worldX = 37 * gp.tileSize;
        gp.obj[1].worldY = 37 * gp.tileSize;

        gp.obj[2] = new OBJ_Oil(gp);
        gp.obj[2].worldX = 32 * gp.tileSize;
        gp.obj[2].worldY = 35 * gp.tileSize;

        gp.obj[3] = new OBJ_Door(gp);
        gp.obj[3].worldX = 11 * gp.tileSize;
        gp.obj[3].worldY = 39 * gp.tileSize;

        gp.obj[4] = new OBJ_Key(gp);
        gp.obj[4].worldX = 26 * gp.tileSize;
        gp.obj[4].worldY = 42 * gp.tileSize;

        gp.obj[5] = new OBJ_Door(gp);
        gp.obj[5].worldX = 25 * gp.tileSize;
        gp.obj[5].worldY = 26 * gp.tileSize;

        gp.obj[6] = new OBJ_Door(gp);
        gp.obj[6].worldX = 38 * gp.tileSize;
        gp.obj[6].worldY = 11 * gp.tileSize;

        gp.obj[7] = new OBJ_Key(gp);
        gp.obj[7].worldX = 46 * gp.tileSize;
        gp.obj[7].worldY = 10 * gp.tileSize;

        gp.obj[8] = new OBJ_Finish(gp);
        gp.obj[8].worldX = 40 * gp.tileSize;
        gp.obj[8].worldY = 30 * gp.tileSize;

        gp.obj[9] = new OBJ_Speed_Up(gp);
        gp.obj[9].worldX = 5 * gp.tileSize;
        gp.obj[9].worldY = 42 * gp.tileSize;

        gp.obj[10] = new OBJ_Finish(gp);
        gp.obj[10].worldX = 41 * gp.tileSize;
        gp.obj[10].worldY = 30 * gp.tileSize;

        gp.obj[11] = new OBJ_Finish(gp);
        gp.obj[11].worldX = 42 * gp.tileSize;
        gp.obj[11].worldY = 30 * gp.tileSize;

        gp.obj[12] = new OBJ_Finish(gp);
        gp.obj[12].worldX = 43 * gp.tileSize;
        gp.obj[12].worldY = 30 * gp.tileSize;

        gp.obj[13] = new OBJ_Finish(gp);
        gp.obj[13].worldX = 44 * gp.tileSize;
        gp.obj[13].worldY = 30 * gp.tileSize;

        gp.obj[14] = new OBJ_Spike(gp);
        gp.obj[14].worldX = 19 * gp.tileSize;
        gp.obj[14].worldY = 37 * gp.tileSize;

        gp.obj[15] = new OBJ_Spike(gp);
        gp.obj[15].worldX = 6 * gp.tileSize;
        gp.obj[15].worldY = 5 * gp.tileSize;

        gp.obj[16] = new OBJ_Spike(gp);
        gp.obj[16].worldX = 24 * gp.tileSize;
        gp.obj[16].worldY = 25 * gp.tileSize;

        gp.obj[17] = new OBJ_Spike(gp);
        gp.obj[17].worldX = 24 * gp.tileSize;
        gp.obj[17].worldY = 27 * gp.tileSize;

        gp.obj[18] = new OBJ_Spike(gp);
        gp.obj[18].worldX = 23 * gp.tileSize;
        gp.obj[18].worldY = 27 * gp.tileSize;

        gp.obj[19] = new OBJ_Spike(gp);
        gp.obj[19].worldX = 22 * gp.tileSize;
        gp.obj[19].worldY = 27 * gp.tileSize;

        gp.obj[20] = new OBJ_Speed_Up_Down(gp);
        gp.obj[20].worldX = 42 * gp.tileSize;
        gp.obj[20].worldY = 7 * gp.tileSize;

        gp.obj[21] = new OBJ_Spike(gp);
        gp.obj[21].worldX = 35 * gp.tileSize;
        gp.obj[21].worldY = 25 * gp.tileSize;

        gp.obj[22] = new OBJ_Coin(gp);
        gp.obj[22].worldX = 41 * gp.tileSize;
        gp.obj[22].worldY = 39 * gp.tileSize;

        gp.obj[23] = new OBJ_Coin(gp);
        gp.obj[23].worldX = 29 * gp.tileSize;
        gp.obj[23].worldY = 40 * gp.tileSize;

        gp.obj[24] = new OBJ_Coin(gp);
        gp.obj[24].worldX = 22 * gp.tileSize;
        gp.obj[24].worldY = 40 * gp.tileSize;

        gp.obj[25] = new OBJ_Coin(gp);
        gp.obj[25].worldX = 4 * gp.tileSize;
        gp.obj[25].worldY = 35 * gp.tileSize;

        gp.obj[26] = new OBJ_Coin(gp);
        gp.obj[26].worldX = 12 * gp.tileSize;
        gp.obj[26].worldY = 17 * gp.tileSize;

        gp.obj[27] = new OBJ_Coin(gp);
        gp.obj[27].worldX = 22 * gp.tileSize;
        gp.obj[27].worldY = 16 * gp.tileSize;

        gp.obj[28] = new OBJ_Coin(gp);
        gp.obj[28].worldX = 44 * gp.tileSize;
        gp.obj[28].worldY = 18 * gp.tileSize;

        gp.obj[29] = new OBJ_Coin(gp);
        gp.obj[29].worldX = 40 * gp.tileSize;
        gp.obj[29].worldY = 19 * gp.tileSize;
    }
}
