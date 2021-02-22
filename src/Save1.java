import java.io.*;
import java.util.*;

public class Save1 {
    public void loadSave(File loadPath) {
        try {
            Scanner loadScanner = new Scanner(loadPath);

            while (loadScanner.hasNext()) {
                GameField.killsToWin = loadScanner.nextInt();              
                for (int y=0; y<GameField.map.block.length; y++) {
                    for (int x=0; x<GameField.map.block[0].length; x++) {
                        GameField.map.block[y][x].groundID = loadScanner.nextInt();
                    }
                }
                
                for (int y=0; y<GameField.map.object.length; y++) {
                    for (int x=0; x<GameField.map.object[0].length; x++) {
                        GameField.map.object[y][x].airID = loadScanner.nextInt();
                    }
                }
            }
            loadScanner.close();
        } catch (Exception e) { }
    }
}
