import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

public class Black extends Chip {
    Black(int X, int Y) {
        super(X, Y);
    }

    static boolean IsBlackInList(LinkedList<Black> list, Black bl){
        boolean flag = false;
        for (Black el:list) {
            if (el.a == bl.a & el.b == bl.b) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public HashSet<Black> PossibleMoves(Chip[][] field) {
        HashSet<Black> list = new HashSet<>();
        boolean edgeFlag = false;
        for (int x = Math.max(a - 1, 0); x <= Math.min(a + 1, 8 - 1); x++) {
            for (int y = Math.max(b - 1, 0); y <= Math.min(b + 1, 8 - 1); y++) {
                if (x == a && y == b)
                    continue;
                if (field[x][y] != null && field[x][y].getClass() == White.class) {
                    int deltaX = x - a;
                    int deltaY = y - b;
                    int xx = x;
                    int yy = y;
                    while (field[xx][yy] != null && field[xx][yy].getClass() != null) {
                        xx += deltaX;
                        yy += deltaY;
                        if (xx >= 8 || yy >= 8 || xx < 0 || yy < 0) {
                            if (xx >= 8) xx--;
                            if (yy >= 8) yy--;
                            if (xx < 0) xx++;
                            if (yy < 0) yy++;
                            edgeFlag = true;
                            break;
                        }
                    }
                    if (field[xx - deltaX][yy - deltaY] != null && field[xx - deltaX][yy - deltaY].getClass() == White.class) {
                        if (!list.contains(new Black(xx, yy)) && !edgeFlag) {
                            list.add(new Black(xx, yy));
                        }
                    }
                    edgeFlag = false;
                }
            }
        }
        return list;
    }

    public double AnalizeBlackMove(Chip[][] field) {
        double value = 0;
        var fieldCopy = new Chip[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (field[i][j] != null) {
                    var chip = field[i][j];
                    fieldCopy[i][j] = chip.getClass() == White.class ? new White(chip.a, chip.b) : new Black(chip.a, chip.b);
                } else {
                    fieldCopy[i][j] = null;
                }
            }
        }
        if (a == 0 && b == 0)
            value += 0.8;
        else if (a == 7 && b == 7)
            value += 0.8;
        else if (a == 0 && b == 7)
            value += 0.8;
        else if (a == 7 && b == 0)
            value += 0.8;
        else if (a == 0 || a == 7)
            value += 0.4;
        else if (b == 0 || b == 7)
            value += 0.4;
        Black fakeChip = new Black(a, b);
        var list = new ArrayList<Chip>();
        for (int x = Math.max(fakeChip.a - 1, 0); x <= Math.min(fakeChip.a + 1, 8 - 1); x++) {
            for (int y = Math.max(fakeChip.b - 1, 0); y <= Math.min(fakeChip.b + 1, 8 - 1); y++) {
                if (x == fakeChip.a && y == fakeChip.b)
                    continue;
                if (fieldCopy[x][y] != null && fieldCopy[x][y].getClass() == White.class) {
                    int deltaX = x - fakeChip.a;
                    int deltaY = y - fakeChip.b;
                    int xx = x;
                    int yy = y;
                    var tmp = new ArrayList<Black>();
                    while (fieldCopy[xx][yy] != null && fieldCopy[xx][yy].getClass() != null) {
                        tmp.add(new Black(xx, yy));
                        xx += deltaX;
                        yy += deltaY;
                        if (xx >= 8 || yy >= 8 || xx < 0 || yy < 0) {
                            if (xx >= 8) xx--;
                            if (yy >= 8) yy--;
                            if (xx < 0) xx++;
                            if (yy < 0) yy++;
                            break;
                        }
                        if (field[xx][yy] != null && fieldCopy[xx][yy].getClass() == Black.class)
                            break;
                    }
                    if (fieldCopy[xx][yy] != null && fieldCopy[xx][yy].getClass() == Black.class) {
                        for (var elem : tmp) {
                            list.add(new Black(elem.a, elem.b));
                        }
                    }
                    tmp.clear();
                }
            }
        }
        for (var c : list) {
            if (c.a == 0 || c.a == 7)
                value += 2;
            else if (c.b == 0 || c.b == 7)
                value += 2;
            else
                value += 1;
        }

        return value;
    }

    public void BlackMove(Chip[][] field) {
        field[a][b] = new Black(a, b);
        for (int x = Math.max(a - 1, 0); x <= Math.min(a + 1, 8 - 1); x++) {
            for (int y = Math.max(b - 1, 0); y <= Math.min(b + 1, 8 - 1); y++) {
                if (x == a && y == b)
                    continue;
                if (field[x][y] != null && field[x][y].getClass() == White.class) {
                    int deltaX = x - a;
                    int deltaY = y - b;
                    int xx = x;
                    int yy = y;
                    var tmp = new ArrayList<Black>();
                    while (field[xx][yy] != null && field[xx][yy].getClass() != null) {
                        tmp.add(new Black(xx, yy));
                        xx += deltaX;
                        yy += deltaY;
                        if (xx >= 8 || yy >= 8 || xx < 0 || yy < 0) {
                            if (xx >= 8) xx--;
                            if (yy >= 8) yy--;
                            if (xx < 0) xx++;
                            if (yy < 0) yy++;
                            break;
                        }
                        if (field[xx][yy] != null && field[xx][yy].getClass() == Black.class)
                            break;
                    }
                    if (field[xx][yy] != null && field[xx][yy].getClass() == Black.class) {
                        for (var elem : tmp) {
                            field[elem.a][elem.b] = new Black(elem.a, elem.b);
                        }
                    }
                    tmp.clear();
                }
            }
        }
    }

}
