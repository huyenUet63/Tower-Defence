import java.awt.*;

public abstract class Enemy1 extends Rectangle {
    public int xC, yC;
    public int blood;
    public int bloodSpace = 3, bloodHeight = 6;
    public int enemySize = 52;
    public int enemyWalk = 0;
    public int upward = 0, downward = 1, right = 2, left = 3;
    public int direction = right;
    public int enemyID = Value1.enemyAir;
    public boolean inGame = false;
    public boolean hasUpward = false;
    public boolean hasDownward = false;
    public boolean hasLeft = false;
    public boolean hasRight = false;
    public int walkSpeed;// = 30;


    public Enemy1(/*int x, int y, int width, int height/*, int enemyID*/) {
        //super(x, y, width, height);
        //this.enemyID = enemyID;
    }
    
    public Point findSpawner() {
        Point p = new Point(0, 0);
        for (int y=0; y<GameField.map.object.length; y++) {
            for (int x=0; x<GameField.map.object[0].length; x++) {
                if (GameField.map.object[y][x].airID == Value1.spawnPoint) {
                    p = new Point(x,y);
                    return p;
                }
            }
        }
        return p;
    }
//    
//    public Point findSpawner() {
//        Point spawn = null;
//        for (int y=0; y<GameField.map.block.length; y++) {
//            if (GameField.map.block[y][0].groundID == Value1.groundRock && GameField.map.block[y][0].groundID != Value1.airCave) {
//                spawn.x = 0;
//                spawn.y = y;
//                return spawn;
//            }
//            if (GameField.map.block[0][y].groundID == Value1.groundRock && GameField.map.block[0][y].groundID != Value1.airCave) {
//                spawn.x = y;
//                spawn.y = 0;
//                return spawn;
//            }
//        }
//        for (int y=0; y<GameField.map.block.length; y++) {
//            if (GameField.map.block[y][0].groundID == Value1.groundRock && GameField.map.block[y][0].groundID != Value1.airCave) {
//                spawn.x = 0;
//                spawn.y = y;
//                return spawn;
//            }
//            if (GameField.map.block[0][y].groundID == Value1.groundRock && GameField.map.block[0][y].groundID != Value1.airCave) {
//                spawn.x = y;
//                spawn.y = 0;
//                return spawn;
//            }
//        }
//        return spawn;
//    }
    
    public void spawnEnemy(int enemyID) {
        for(int y=0; y<GameField.map.block.length; y++)
            for (int x=0; x<GameField.map.block[0].length; x++) {
            if(GameField.map.block[y][0].groundID == Value1.spawnPoint) {
        setBounds(GameField.map.block[y][x].x, GameField.map.block[y][x].y, enemySize, enemySize);
        xC = x;
        yC = y;
        break;
            }
        }
        this.enemyID = enemyID;
        this.blood = enemySize;
        inGame = true;
        //GameField.num += 1;
    }
    
    
    
    public void deleteEnemy() {
        inGame = false;
        direction = right;
        enemyWalk = 0;
    }

    public void loseHealth() {
        GameField.health -= 1;
    }

    public int walkFrame = 0;//, walkSpeed = 30;
    public void physic() {
        if (walkFrame >= walkSpeed) {
            if (direction == right) {
                x += 1;
            } else if (direction == upward) {
                y -= 1;
            } else if (direction == downward) {
                y += 1;
            } else if (direction == left) {
                x -= 1;
            }

            enemyWalk += 1;

            if (enemyWalk == GameField.map.blockSize) {
                if (direction == right) {
                    xC += 1;
                    hasRight = true;
                } else if (direction == upward) {
                    yC -= 1;
                    hasUpward = true;
                } else if (direction == downward) {
                    yC += 1;
                    hasDownward = true;
                } else if (direction == left) {
                    xC -= 1;
                    hasLeft = true;
                }

                if (!hasUpward) {
                    try {
                        if (GameField.map.block[yC + 1][xC].groundID == Value1.groundRock) {
                            direction = downward;
                        }
                    } catch (Exception e) {
                    }
                }

                if (!hasDownward) {
                    try {
                        if (GameField.map.block[yC - 1][xC].groundID == Value1.groundRock) {
                            direction = upward;
                        }
                    } catch (Exception e) {}
                }
                        if (GameField.map.block[yC][xC - 1].groundID == Value1.groundRock) 

                if (!hasRight) {
                    try {
                        if (GameField.map.block[yC][xC - 1].groundID == Value1.groundRock) {
                            direction = left;
                        }
                    } catch (Exception e) {
                    }
                }

                if (!hasLeft) {
                    try {
                        if (GameField.map.block[yC][xC + 1].groundID == Value1.groundRock) {
                            direction = right;
                        }
                    } catch (Exception e) {
                    }
                }

                if (GameField.map.object[yC][xC].airID == Value1.target) {
                    deleteEnemy();
                    loseHealth();
                }

                hasUpward = false;
                hasDownward = false;
                hasLeft = false;
                hasRight = false;
                enemyWalk = 0;
            }

            walkFrame = 0;
        } else {
            walkFrame += 1;
        }
    }
    
    public void loseBlood(int amo) {
        blood -= amo;
        checkDeath();
    }
    
    public void checkDeath() {
        if (blood <= 0) {
            deleteEnemy();
            getDrop();
            GameField.killed += 1;
        }
    }
    
    public boolean isDead() {
        return !inGame;
    }

    public abstract void draw (Graphics g);
    public abstract void getDrop(/*int enemyID*/);
    
    public void setWalkSpeed(int walkSpeed) {
        this.walkSpeed = walkSpeed;
    }
    public int getWalkSpeed(int getWalkSpeed) {
        return walkSpeed;
    }
}
