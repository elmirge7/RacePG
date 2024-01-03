package Main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import Object.OBJ_Key;
import Object.ICO_Time;
import Object.ICO_Heart_4;
import Object.ICO_Heart_3;
import Object.ICO_Heart_2;
import Object.ICO_Heart_1;
import Object.ICO_Heart_0;

public class UI {
    GamePanel gp;
    Font arial_40, arial_80B;
    BufferedImage keyImage, timeImage, heart4Image, heart3Image, heart2Image, heart1Image, heart0Image;
    public boolean messageOn = false;
    public String message = "";

    public int messageCounter = 0;
    public boolean gameFinished = false;
    public boolean gameLoosed = false;

    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp) {
        this.gp = gp;

        arial_40 = new Font("QuickSilver", Font.PLAIN, 40);
        arial_80B = new Font("QuickSilver", Font.BOLD, 80);
        OBJ_Key key = new OBJ_Key(gp);
        keyImage = key.image;
        ICO_Time time = new ICO_Time(gp);
        timeImage = time.image;
        ICO_Heart_4 heart_4 = new ICO_Heart_4(gp);
        heart4Image = heart_4.image;
        ICO_Heart_3 heart_3 = new ICO_Heart_3(gp);
        heart3Image = heart_3.image;
        ICO_Heart_2 heart_2 = new ICO_Heart_2(gp);
        heart2Image = heart_2.image;
        ICO_Heart_1 heart_1 = new ICO_Heart_1(gp);
        heart1Image = heart_1.image;
        ICO_Heart_0 heart_0 = new ICO_Heart_0(gp);
        heart0Image = heart_0.image;
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }
    public void draw(Graphics2D g2) {

        if (gameFinished == true) {
            g2.setFont(arial_40);
            g2.setColor(Color.white);

            gp.player.score += (200 - playTime*3);

            String text;
            int textLength;
            int x;
            int y;

            text = "La course est terminée !";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 - (gp.tileSize*3);
            g2.drawString(text, x, y);

            text = "Ton temps est de :" + dFormat.format(playTime) + "!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 + (gp.tileSize*4);
            g2.drawString(text, x, y);

            g2.setFont(arial_80B);
            g2.setColor(Color.yellow);
            text = "Score : " + gp.player.score;
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 + (gp.tileSize*3);
            g2.drawString(text, x, y);

            gp.gameThread = null;

        } else if (gameLoosed == true) {

            g2.setFont(arial_40);
            g2.setColor(Color.white);

            String text;
            int textLength;
            int x;
            int y;

            text = "Tu es mort, tu as perdu tous tes PV";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 - (gp.tileSize*3);
            g2.drawString(text, x, y);

            text = "Evite les spikes, la prochaine fois";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 + (gp.tileSize*4);
            g2.drawString(text, x, y);

            g2.setFont(arial_80B);
            g2.setColor(Color.yellow);
            text = "Essaye encore";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 + (gp.tileSize*3);
            g2.drawString(text, x, y);

            gp.gameThread = null;

        } else {
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            g2.drawImage(keyImage, gp.tileSize/2, 90, gp.tileSize, gp.tileSize, null);
            g2.drawString("x "+ gp.player.hasKey, 74, 130);

            // Life points
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            if (gp.player.lifePoints >= 4) {
                g2.drawImage(heart4Image, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
            } else if (gp.player.lifePoints == 3) {
                g2.drawImage(heart3Image, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
            } else if (gp.player.lifePoints == 2) {
                g2.drawImage(heart2Image, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
            } else if (gp.player.lifePoints == 1) {
                g2.drawImage(heart1Image, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
            } else{
                g2.drawImage(heart0Image, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
            }

            // Turn /2
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            g2.drawString("Turn : " + gp.player.turnNumber  + "/2", 10, 550);


            // Speed Km/h
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            g2.drawString("Max Speed : " + (gp.player.speed + gp.player.speedBoost)*15 + "KM/H", 300, 550);

            //TIME
            playTime +=(double)1/60;
            g2.drawImage(timeImage, 550, 26, gp.tileSize, gp.tileSize, null);
            g2.drawString(": " +dFormat.format(playTime), 620, 65);

            // MESSAGE
            if (messageOn == true) {
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message, gp.tileSize/2, gp.tileSize*5);

                messageCounter++;

                if (messageCounter > 120) {
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }


    }
}
