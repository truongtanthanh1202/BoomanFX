package booman_fx.game;

import booman_fx.Enum.Direction;
import booman_fx.Enum.TypeMap;
import booman_fx.Enum.TypeSprite;
import booman_fx.game.Pair;
import booman_fx.engine.Sprite;
import booman_fx.objects.Bomb;
import booman_fx.objects.Item.PowerItem;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import static booman_fx.Enum.Direction.*;
import static booman_fx.Enum.TypeSprite.*;
import static booman_fx.game.GameState.*;

public class Map {
    public static TypeMap type = TypeMap.CANDY;
    private Images background;
    private Images[] wall;
    private Images[] box;

    public static final int[] dx = {-1, 1, 0, 0};
    public static final int[] dy = {0, 0, 1, -1};
    public static final int[] NUM_BOX = {30, 40, 50, 60, 60}; //limit Num_box = 60, nếu quá 60 sẽ không đặt được box theo yêu cầu

    private final Square[][] map; //Lưu map text vào đây
    private final int width;
    private final int height;
    private int num_box;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void addSprite(Sprite sprite) {
        map[sprite.getYInMap()][sprite.getXInMap()].add(sprite.getTypeSprite());
        if (sprite instanceof Bomb) {
            map[sprite.getYInMap()][sprite.getXInMap()].setPowerBomb(((Bomb) sprite).getPower());
        }
    }

    public void removeSprite(Sprite sprite) {
        map[sprite.getYInMap()][sprite.getXInMap()].remove(sprite.getTypeSprite());
        if (sprite instanceof Bomb) {
            map[sprite.getYInMap()][sprite.getXInMap()].setPowerBomb(((Bomb) sprite).getPower());
        }
    }

    public Map(int width, int height, int level) {
        map = new Square[height][width];
        this.width = width;
        this.height = height;
        num_box = NUM_BOX[level - 1];
        setImage();
        randomMap();
    }

    private void setImage() {
        background = Images.map[type.ordinal()][0];
        wall = new Images[]{
                Images.map[type.ordinal()][1],
                Images.map[type.ordinal()][2]
        };
        box = new Images[]{
                Images.map[type.ordinal()][3],
                Images.map[type.ordinal()][4],
                Images.map[type.ordinal()][5],
        };
    }

    // tạo map text
    public void randomMap() {
        // reset map
        for (int h = 0; h < height; ++h) {
            for (int w = 0; w < width; ++w) {
                map[h][w] = new Square();
            }
        }

        // create wall of border - chieu doc
        for (int h = 0; h < height; h++) {
            map[h][0].add(WALL);
            map[h][width - 1].add(WALL);
        }
        // create wall of border - chieu ngang
        for (int w = 0; w < width; w++) {
            map[0][w].add(WALL);
            map[height - 1][w].add(WALL);
        }

        // create character, player mặc định ở (1,1) và enemy ở 3 góc còn lại
        map[1][1].add(PLAYER);
        map[height - 2][1].add(ENEMY);
        map[1][width - 2].add(ENEMY);
        map[height - 2][width - 2].add(ENEMY);

        // tạo tường cố định để ngăn enemy đi thẳng đến player
        map[8][1].add(WALL);
        map[1][8].add(WALL);
        map[13][8].add(WALL);
        map[6][15].add(WALL);

        //Tạo những mảnh tường cách nhau 1 ô
        for (int h = 2; h < height - 2; h += 2) {
            for (int w = 2; w < width - 2; w += 2) {
                map[h][w].add(WALL);
            }
        }

        //Ném wall linh tinh vào map
        Random random = new Random();

        //Thêm wall vào theo chiều ngang
        for (int h = 2; h < height - 2; h += 2) {
            int numWall = Math.abs(random.nextInt()) % (2) + 2; // numWall chỉ = 2 hoặc 3

            switch (numWall) {
                case 2 -> {
                    int w1 = Math.abs(random.nextInt()) % (4);
                    w1++;
                    int w2 = Math.abs(random.nextInt()) % (6 - w1 - 1);
                    w2 += w1 + 2;
                    map[h][2 * w1 + 1].add(WALL);
                    map[h][2 * w2 + 1].add(WALL);
                }
                case 3 -> {
                    int w1 = Math.abs(random.nextInt()) % (2);
                    w1++;
                    int w2;
                    int w3;
                    if (w1 == 2) {
                        w2 = 4;
                        w3 = 6;
                    } else {
                        w2 = Math.abs(random.nextInt()) % (2);
                        w2 += w1 + 2;
                        w3 = Math.abs(random.nextInt()) % (6 - w2 - 1);
                        w3 += w2 + 2;
                    }
                    map[h][2 * w1 + 1].add(WALL);
                    map[h][2 * w2 + 1].add(WALL);
                    map[h][2 * w3 + 1].add(WALL);
                }
            }
        }

        //thêm wall theo chiều dọc
        for (int w = 2; w < width - 2; w += 2) {
            ArrayList<Integer> vtWall = new ArrayList<>();
            for (int h = 3; h < height - 2; h += 2) {
                if (checkWall(h, w)) vtWall.add(h);
            }
            int h = vtWall.get(Math.abs(random.nextInt()) % (vtWall.size()));
            map[h][w].add(WALL);
        }

        //Ném từng box vào map cho đến khi num_box = 0
        while (num_box > 0) {
            int w = Math.abs(random.nextInt()) % width;
            int h = Math.abs(random.nextInt()) % height;

            Pair pair = new Pair(w, h);
            //Khống chế không cho 2 box không ở ngay cạnh player và enemy
            boolean notNextToPlayer = pair.equals(new Pair(1, 2)) || pair.equals(new Pair(2, 1));
            boolean notNextToEnemyRT = pair.equals(new Pair(width - 3, 1)) || pair.equals(new Pair(width - 2, 2));
            boolean notNextToEnemyLB = pair.equals(new Pair(1, height - 3)) || pair.equals(new Pair(2, height - 2));
            boolean notNextToEnemyRB = pair.equals(new Pair(width - 2, height - 3)) || pair.equals(new Pair(width - 3, height - 2));
            if (notNextToPlayer || notNextToEnemyRB || notNextToEnemyLB || notNextToEnemyRT) {
                continue;
            }

            //ném box vào nếu thỏa mãn checkEmpty() và checkConsecutiveBox()
            if (map[h][w].checkEmpty() && checkConsecutiveBox(h, w)) {
                map[h][w].add(BOX);
                num_box--;
            }
        }

        //Export map to file txt
        try {
            exportMapToFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // check xem khi thêm 1 WALL có thỏa mãn điều kiện hàm dfs() hay không
    private boolean checkWall(int h, int w) {
        boolean wall = true;
        map[h][w].add(WALL);
        boolean[][] visited = new boolean[height][width];
        dfs(1, 1, visited);
        for (int _h = 0; _h < height; ++_h) {
            for (int _w = 0; _w < width; ++_w) {
                if (!visited[_h][_w] && !map[_h][_w].getTypeSprite(WALL)) {
                    wall = false;
                    _h = 100;
                    break;
                }
            }
        }
        map[h][w].remove(WALL);
        return wall;
    }

    //check có tồn tại 1 tường khép kín trong 1 map này không
    //dùng lan để tìm xem 1 ô không đi vào được
    /**
     *  i = 0, check ô bên phải (của ô đang check)
     */
    //public static final int[] dx = {-1, 1, 0, 0};
    //public static final int[] dy = {0, 0, 1, -1};
    private void dfs(int h, int w, boolean[][] visited) {
        visited[h][w] = true;
        for (int i = 0; i < 4; ++i) {
            int _h = h + dy[i];
            int _w = w + dx[i];
            //nếu lan đến 1 ô thì không lan nữa
            //nếu là ô trong 1 bảng thì lan đến
            //nếu ô đấy là WAll thì không lan đến
            if (0 <= _h && !visited[_h][_w]
                    && _h < height && 0 <= _w && _w < width
                    && !map[_h][_w].getTypeSprite(WALL)) {
                dfs(_h, _w, visited);
            }
        }
    }

    //Kiểm tra dấu + quanh ô định đặt box có tạo thành 3 box liên tiếp không
    private boolean checkConsecutiveBox(int h, int w) {
        int numBoxX = 0, numBoxY = 0;
        int maxBoxInRange = 2;
        for (int i = 1; i < maxBoxInRange + 1; i++) {
            if (h-i >= 0) {
                if (map[h-i][w].getTypeSprite(BOX)) numBoxY++;
            }
            if (h+i < HEIGHT / SIZE_A_SQUARE) {
                if (map[h+i][w].getTypeSprite(BOX)) numBoxY++;
            }
            if (w-i >= 0) {
                if (map[h][w-i].getTypeSprite(BOX)) numBoxX++;
            }
            if (w+i < WIDTH / SIZE_A_SQUARE) {
                if (map[h][w+i].getTypeSprite(BOX)) numBoxX++;
            }
        }
        return (numBoxX < 2) && (numBoxY < 2);
    }

    private void exportMapToFile() throws IOException {
        FileOutputStream outputStream = new FileOutputStream("src/main/resources/booman_fx/map/level-map.txt");

        String level = "level: " + App.gameAttribute.getLevel() + "\n";
        outputStream.write(level.getBytes());

        for(int i = 0; i < height; ++ i){
            for(int j = 0; j < width; ++ j){
                if(map[i][j].getTypeSprite(WALL)){
                    outputStream.write('W');
                }
                else if(map[i][j].getTypeSprite(PLAYER)){
                    outputStream.write('P');
                }
                else if(map[i][j].getTypeSprite(ENEMY)){
                    outputStream.write('E');
                }
                else if(map[i][j].getTypeSprite(BOX)){
                    outputStream.write('B');
                }
                else{
                    outputStream.write(' ');
                }
            }
            outputStream.write('\n');
        }
        outputStream.close();
    }

    public boolean checkSquareInMap(int x, int y) {
        return (0 <= x && x < width && 0 < y && y < height);
    }

    private boolean checkDangerInDirection(int x, int y, Direction direct) {
        for (int i = 1; i <= PowerItem.MAX_POWER_ITEM; ++i) {
            x += dx[direct.ordinal()];
            y += dy[direct.ordinal()];

            if (!checkSquareInMap(x, y)) {
                break;
            }

            if (!map[y][x].checkNotExist(new TypeSprite[]{BOX, BOMB, WALL})) {
                return (map[y][x].getPowerBomb() >= i);
            }
        }
        return false;
    }

    // check this square in map is dangerous ?
    public boolean checkDanger(int x, int y) {
        if (map[y][x].getTypeSprite(BOMB)) {
            return true;
        }

        return checkDangerInDirection(x, y, LEFT)
                || checkDangerInDirection(x, y, RIGHT)
                || checkDangerInDirection(x, y, UP)
                || checkDangerInDirection(x, y, DOWN);
    }

    public Images getBackground() {
        return background;
    }

    public Images[] getWall() {
        return wall;
    }

    public Images[] getBox() {
        return box;
    }

    public Square[][] getMap() {
        return map;
    }

    public Pair findEmptySquare() {
        Random random = new Random();
        int w, h;
        do {
            w = Math.abs(random.nextInt()) % width;
            h = Math.abs(random.nextInt()) % height;
        }
        while (!map[h][w].checkEmpty());
        return new Pair(w, h);
    }

//    public static void main(String[] args) {
//        int level = 1;
//        Map m = new Map(WIDTH / SIZE_A_SQUARE, HEIGHT / SIZE_A_SQUARE, level);
//        m.randomMap();
//    }
}
