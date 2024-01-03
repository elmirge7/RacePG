package Entity;

import Main.GamePanel;
import Main.KeyHandler;
import Main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public int hasKey = 0;
    public int lifePoints = 4;
    public int turnNumber = 0;
    public int score = 0;
    private long lastSpikeTime; // Variable pour suivre le dernier moment d'impact des pointes
    private long lastSpeedUpTime; // Variable pour suivre le dernier moment de boost
    private long lastFinishTime; // Variable pour suivre le dernier moment de passage de ligne d'arrivée

    private final long spikeCooldown = 2000; // Délai en millisecondes entre chaque impact de pointe
    private final long speedUpCooldown = 2000; // Délai en millisecondes entre chaque boost

    private int speedBoostDuration = 2000; // Durée du boost en millisecondes

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize / 2);
        screenY = gp.screenHeight/2 - (gp.tileSize / 2);

        solidArea = new Rectangle();
        solidArea.x = 16;
        solidArea.y = 16;
        solidArea.width = 16;
        solidArea.height = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        setDefaultValues();
        getPlayerImage();
        lastSpikeTime = System.currentTimeMillis(); // Initialisation du temps du dernier impact des pointes
        lastSpeedUpTime = System.currentTimeMillis();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 42; // starting position
        worldY = gp.tileSize * 44;
        speed = 8;
        direction = "up";
    }

    public void getPlayerImage() {


            up1 = setup("player_up");
            up2 = setup("player_up_move");
            down1 = setup("player_down");
            down2 = setup("player_down_move");
            left1 = setup("player_left");
            left2 = setup("player_left_move");
            right1 = setup("player_right");
            right2 = setup("player_right_move");


    }

    public BufferedImage setup(String imageName){
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/player/" + imageName +".png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public void update() {

        // Inside the game loop or update method
        if (speedBoost > 1) {
            long currentTime = System.currentTimeMillis();
            long elapsedTime = currentTime - lastSpeedUpTime;

            if (elapsedTime >= speedBoostDuration) {
                speedBoost = 0; // Speed boost duration is over
            }
        }

        if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true || keyH.upLeftPressed == true || keyH.upRightPressed == true || keyH.downLeftPressed == true || keyH.downRightPressed == true) {
            // Détecter la nouvelle direction
            String newDirection = "";

            if (keyH.upPressed) {
                newDirection += "up";
            }
            if (keyH.downPressed) {
                newDirection += "down";
            }
            if (keyH.leftPressed) {
                newDirection += "left";
            }
            if (keyH.rightPressed) {
                newDirection += "right";
            }
            if (keyH.upLeftPressed) {
                newDirection += "upleft";
            }
            if (keyH.upRightPressed) {
                newDirection += "upright";
            }
            if (keyH.downLeftPressed) {
                newDirection += "downleft";
            }
            if (keyH.downRightPressed) {
                newDirection += "downright";
            }

            // Comparer avec la dernière direction
            if (!newDirection.equals(direction)) {
                direction = newDirection;
            }

            // Mise à jour de la dernière direction
            direction = newDirection;

            if (keyH.upPressed == true && keyH.upRightPressed == false && keyH.upLeftPressed == false) {
                direction = "up";
            } else if (keyH.downPressed == true && keyH.downLeftPressed == false && keyH.downRightPressed == false) {
                direction = "down";
            } else if (keyH.leftPressed == true && keyH.downLeftPressed == false && keyH.upLeftPressed == false) {
                direction = "left";
            } else if (keyH.rightPressed == true && keyH.upRightPressed == false && keyH.downRightPressed == false) {
                direction = "right";
            } else if (keyH.upLeftPressed == true) {
                direction = "upleft";
            } else if (keyH.upRightPressed == true) {
                direction = "upright";
            } else if (keyH.downLeftPressed == true) {
                direction = "downleft";
            } else if (keyH.downRightPressed == true) {
                direction = "downright";
            }

            // Check tile collision
            collisionOn = false;
            gp.cChecker.checkTile(this);

            // Check object position
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            // IF COLLISION IS FALSE, PLAYER CAN MOVE
            if (collisionOn == false) {
                switch (direction) {
                    case "up":
                        worldY -= speed + speedBoost;
                        break;
                    case "down":
                        worldY += speed + speedBoost;
                        break;
                    case "left":
                        worldX -= speed + speedBoost;
                        break;
                    case "right":
                        worldX += speed + speedBoost;
                        break;
                    case "upleft":
                        worldY -= speed + speedBoost;
                        worldX -= speed + speedBoost;
                        break;
                    case "upright":
                        worldY -= speed + speedBoost;
                        worldX += speed + speedBoost;
                        break;
                    case "downleft":
                        worldY += speed + speedBoost;
                        worldX -= speed + speedBoost;
                        break;
                    case "downright":
                        worldY += speed + speedBoost;
                        worldX += speed + speedBoost;
                        break;
                }


            }


            spriteCounter++;
            if (spriteCounter > 10) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                }
                else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void pickUpObject(int i) {
        if (i != 999) {
            long currentTime = System.currentTimeMillis();
            String objectName = gp.obj[i].name;

            // Vérifier le délai pour les pointes
            if (objectName.equals("Spike") && currentTime - lastSpikeTime < spikeCooldown) {
                // gp.ui.showMessage("Attendez un moment avant le prochain impact des pointes !");
                return; // Ne pas appliquer l'impact des pointes maintenant
            }

            // Vérifier le délai pour la speed up
            if (objectName.equals("Speed Up") && currentTime - lastSpeedUpTime < speedUpCooldown) {
                // gp.ui.showMessage("Attendez un moment avant le prochain impact des pointes !");
                return; // Ne pas appliquer l'impact des pointes maintenant
            }

            // Vérifier le délai pour la finish
            if (objectName.equals("Finish") && currentTime - lastFinishTime < speedUpCooldown) {
                // gp.ui.showMessage("Attendez un moment avant le prochain impact des pointes !");
                return; // Ne pas appliquer l'impact des pointes maintenant
            }

            switch (objectName) {
                case "Key":
                    gp.playSE(6);
                    hasKey++;
                    gp.obj[i] = null;
                    gp.ui.showMessage("You got a key, you can take a shortcut !");
                    break;
                case "Door":
                    if (hasKey > 0) {
                        gp.playSE(5);
                        gp.obj[i] = null;
                        hasKey--;
                        gp.ui.showMessage("You opened the door !");
                    } else {
                        gp.ui.showMessage("You need a key !");

                    }
                    break;
                case "Info":
                    gp.ui.showMessage("Voici les règles du jeu :");
                    break;
                case "Spike":
                    gp.playSE(1);
                    lifePoints -= 1;
                    gp.ui.showMessage("Aie Pagnan!");
                    lastSpikeTime = currentTime; // Mettre à jour le temps du dernier impact des pointes
                    if (lifePoints == 0) {
                        gp.ui.gameLoosed = true;
                    }
                    break;
                case "Oil":
                    gp.playSE(4);
                    lifePoints+=1;
                    gp.obj[i] = null;
                    gp.ui.showMessage("Ah ça fait du bien!");
                    break;
                case "Speed Up":
                    gp.playSE(3);
                    speedBoost = 2;
                    lastSpeedUpTime = System.currentTimeMillis();
                    gp.ui.showMessage("SUPER SONIC!");
                    break;
                case "Finish":
                    gp.playSE(0);
                    if (direction.equals("up") || direction.equals("upleft") || direction.equals("upright")) {
                        turnNumber--;
                    } else {
                        turnNumber++;
                    }
                    lastFinishTime = System.currentTimeMillis();
                    /*if (turnNumber < 0) {
                        turnNumber = 0;
                    }*/
                    if (turnNumber == 2) {
                        gp.ui.gameFinished = true;
                    }
                    break;
                case "Coin":
                    gp.playSE(2);
                    score += 10;
                    gp.obj[i] = null;
                    break;
            }
        }
    }
    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        switch (direction) {
            case "up":
            case "upright":
            case "upleft":
                if(spriteNum == 1) {
                    image = up1;
                }
                if(spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
            case "downright":
            case "downleft":
                if(spriteNum == 1) {
                    image = down1;
                }
                if(spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if(spriteNum == 1) {
                    image = left1;
                }
                if(spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum == 1) {
                    image = right1;
                }
                if(spriteNum == 2) {
                    image = right2;
                }
                break;
            default:
                break;
        }

        g2.drawImage(image, screenX, screenY, null);
    }
}
