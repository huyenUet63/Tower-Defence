import java.awt.*;

public class Store1 {
    public static int shopSize = 5;
    public static int buttonSize = 52;
    public static int cellSpace = 2;
    public static int awayFromRoom = 29;
    public static int iconSize = 20;
    public static int iconSpace = 6;
    public static int iconTextY = 15;
    public static int itemIn = 4;
    public static int heldID = -1;
    public static int realID = -1;
    
    public static int[] buttonID = {2, 3, 4, Value1.air, Value1.airTrashCan};
    public static int[] buttonPrice = {10, 12, 15, 0 ,0};

    public Rectangle[] button = new Rectangle[shopSize];
    public Rectangle buttonHealth;
    public Rectangle buttonCoins;

    public boolean holdsItem = false;

    public Store1() {
        define();
    }

    public void click (int mouseButton) {
        if (mouseButton == 1) {
            for (int i = 0; i<button.length; i++) {
                if (button[i].contains(GameField.mouse)) {
                    if (buttonID[i] != Value1.air) {
                        if (buttonID[i] == Value1.airTrashCan) {  //Delete item
                            holdsItem = false;
                        } else {
                            heldID = buttonID[i];
                            realID = i;
                            holdsItem = true;
                        }
                    }
                }
            }
            if (holdsItem) {
                if (GameField.coinage >= buttonPrice[realID]) {
                    for (int y=0; y<GameField.map.block.length; y++) {
                        for (int x=0; x<GameField.map.block[0].length; x++) {
                            if (GameField.map.block[y][x].contains(GameField.mouse)) {
                                if (GameField.map.block[y][x].groundID != Value1.groundRock && GameField.map.object[y][x].airID == Value1.air) {
                                    //GameField.map.block[y][x].airID = heldID;
                                    GameField.map.object[y][x].airID = heldID;
                                    Tower t;
                                    switch (heldID){
                                        case 2:
                                           t = new NomalTower(GameField.map.object[y][x].x, GameField.map.object[y][x].y, GameField.map.object[y][x].width, GameField.map.object[y][x].height);
                                           GameField.map.tower1.add(t); 
                                           break;
                                        case 3:
                                            t = new SniperTower(GameField.map.object[y][x].x, GameField.map.object[y][x].y, GameField.map.object[y][x].width, GameField.map.object[y][x].height);
                                            GameField.map.tower1.add(t);
                                            break;
                                        case 4:
                                            t = new GunMachineTower(GameField.map.object[y][x].x, GameField.map.object[y][x].y, GameField.map.object[y][x].width, GameField.map.object[y][x].height);
                                            GameField.map.tower1.add(t);
                                            break;
                                    }                                   
                                    GameField.coinage -= buttonPrice[realID];
                                }
                            }
                        }
                    }
                }
            }            
        }
    }

    public void define() {
        for (int i=0; i<button.length; i++) {
            button[i] = new Rectangle(GameField.myWidth/2 - shopSize*(buttonSize+cellSpace)/2 + (buttonSize+cellSpace)*i, GameField.map.block[GameField.map.worldHeight-1][0].y + GameField.map.blockSize + awayFromRoom, buttonSize, buttonSize);
        }

        buttonHealth = new Rectangle(GameField.map.block[0][0].x - 1, button[0].y + shopSize, iconSize, iconSize);
        buttonCoins = new Rectangle(GameField.map.block[0][0].x - 1, button[0].y + shopSize + 50, iconSize, iconSize);
    }

    public void draw(Graphics g) {
        for (int i=0; i<button.length; i++) {
            if (button[i].contains(GameField.mouse)) {
                g.setColor(new Color(255, 255, 255, 150));
                g.fillRect(button[i].x, button[i].y, button[i].width, button[i].height);
            }

            g.drawImage(GameField.set_cell[0], button[i].x, button[i].y, button[i].width, button[i].height, null);
            if (buttonID[i] != Value1.air) {
                g.drawImage(GameField.set_air[buttonID[i]], button[i].x + itemIn, button[i].y + itemIn, button[i].width - (itemIn * 2), button[i].height - (itemIn * 2), null);
            }

            if (buttonPrice[i] > 0) {
                g.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
                g.setColor(new Color(255, 255, 255));
                g.drawString("$" + buttonPrice[i] + "", button[i].x + itemIn, button[i].y + itemIn);
            }
        }

        g.drawImage(GameField.set_cell[1], buttonHealth.x, buttonHealth.y, buttonHealth.width, buttonHealth.height, null);
        g.drawImage(GameField.set_cell[2], buttonCoins.x, buttonCoins.y, buttonCoins.width, buttonCoins.height, null);
        g.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
        g.setColor(new Color(255, 255, 255));
        g.drawString("" + GameField.health, buttonHealth.x + buttonHealth.width + iconSpace, buttonHealth.y + iconTextY);
        g.drawString("" + GameField.coinage, buttonCoins.x + buttonCoins.width + iconSpace, buttonCoins.y + iconTextY);

        if (holdsItem) {
            g.drawImage(GameField.set_air[heldID], GameField.mouse.x - (button[0].width - (itemIn*2)/2), GameField.mouse.y - (button[0].width - (itemIn*2)/2), button[0].width - (itemIn*2), button[0].height - (itemIn*2), null);
            // ve tower
        }

    }
}
