package Main;

import Entity.Player;
import Tile.TileManager;
import Object.SuperObject;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16*16 tiles
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48*48 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12; // c'est du 4/3
    public final int screenWidth = tileSize * maxScreenCol; // 768px
    public final int screenHeight = tileSize * maxScreenRow; // 576px

    // World settings
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    // FPS
    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Sound music = new Sound();
    Sound se = new Sound();

    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    Thread gameThread;

    // ENTITY AND OBJECT

    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[30];

    TileManager tileM = new TileManager(this);


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(new Color(87, 183, 231));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame() {
        aSetter.setObject();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                // System.out.println("FPS:" + drawCount);
                drawCount = 0;
                timer = 0;
            }

         }

    }

    public void update() {

        player.update();

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        // DEBUG
        long drawStart = 0;
        if(keyH.checkDrawTime == true) {
            drawStart = System.nanoTime();
        }

        // TILE
        tileM.draw(g2);

        //OBJECT
        for(int i = 0; i < obj.length; i++) {
            if(obj[i] != null) {
                obj[i].draw(g2, this);
            }
        }

        //PLAYER
        player.draw(g2);

        //UI
        ui.draw(g2);

        // DEBUG
        if (keyH.checkDrawTime == true) {
            long drawEnd = System.nanoTime();
            long passedTime = drawEnd - drawStart;
            g2.setColor(Color.white);
            g2.drawString("Draw Time : " + passedTime, 10, 400);
            System.out.println("Draw Time : "+ passedTime);
        }

        g2.dispose();
    }


    public void stopMusic() {
        music.stop();
    }

    public void playSE(int i) {
        se.setFile(i);
        se.play();
    }
}
