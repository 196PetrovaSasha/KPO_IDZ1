import java.util.ArrayList;
import java.util.HashSet;

public class White extends Chip {

    White(int x, int y) {
        super(x, y);
    }

    public HashSet<White> PosibleMoves(Chip[][] field) {
        HashSet<White> list = new HashSet<>();
        boolean edgeFlag = false;
        for (int x = Math.max(a - 1, 0); x <= Math.min(a + 1, 8 - 1); x++) {
            for (int y = Math.max(b - 1, 0); y <= Math.min(b + 1, 8 - 1); y++) {
                if (x == a && y == b)
                    continue;
                if (field[x][y] != null && field[x][y].getClass() == Black.class) {
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
                    if (field[xx - deltaX][yy - deltaY] != null && field[xx - deltaX][yy -deltaY].getClass() == Black.class) {
                        if (!list.contains(new White(xx, yy)) && !edgeFlag) {
                            list.add(new White(xx, yy));
                        }
                    }
                    edgeFlag = false;
                }
            }
        }
        return list;
    }

    public double AnalyzeWhiteMove(Chip[][] field) {
        double value = 0;
        Chip[][] fieldCopy = new Chip[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (field[i][j] != null) {
                    Chip chip = field[i][j];
                    fieldCopy[i][j] = chip.getClass() == White.class ? new White(chip.a, chip.b) : new Black(chip.a, chip.b);
                } else {
                    fieldCopy[i][j] = null;
                }
            }
        }
        if (a == 0 && b == 0) {
            value += 0.8;
        }
        else if (a == 7 && b == 7) {
            value += 0.8;
        }
        else if (a == 0 && b == 7) {
            value += 0.8;
        }
        else if (a == 7 && b == 0) {
            value += 0.8;
        }
        else if (a == 0 || a == 7) {
            value += 0.4;
        }
        else if (b == 0 || b == 7) {
            value += 0.4;
        }
        White fakeChip = new White(a, b);
        var list = new ArrayList<Chip>();
        for (int x = Math.max(fakeChip.a - 1, 0); x <= Math.min(fakeChip.a + 1, 8 - 1); x++) {
            for (int y = Math.max(fakeChip.b - 1, 0); y <= Math.min(fakeChip.b + 1, 8 - 1); y++) {
                if (x == fakeChip.a && y == fakeChip.b)
                    continue;
                if (fieldCopy[x][y] != null && fieldCopy[x][y].getClass() == Black.class) {
                    int deltaX = x - fakeChip.a;
                    int deltaY = y - fakeChip.b;
                    int xx = x;
                    int yy = y;
                    var tmp = new ArrayList<White>();
                    while (fieldCopy[xx][yy] != null && fieldCopy[xx][yy].getClass() != null) {
                        tmp.add(new White(xx, yy));
                        xx += deltaX;
                        yy += deltaY;
                        if (xx >= 8 || yy >= 8 || xx < 0 || yy < 0) {
                            if (xx >= 8) xx--;
                            if (yy >= 8) yy--;
                            if (xx < 0) xx++;
                            if (yy < 0) yy++;
                            break;
                        }
                        if (field[xx][yy] != null && fieldCopy[xx][yy].getClass() == White.class)
                            break;
                    }
                    if (fieldCopy[xx][yy] != null && fieldCopy[xx][yy].getClass() == White.class) {
                        for (var elem : tmp) {
                            list.add(new White(elem.a, elem.b));
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

    public void WhiteMove(Chip[][] field) {
        field[a][b] = new White(a, b);
        for (int x = Math.max(a - 1, 0); x <= Math.min(a + 1, 8 - 1); x++) {
            for (int y = Math.max(b - 1, 0); y <= Math.min(b + 1, 8 - 1); y++) {
                if (x == a && y == b)
                    continue;
                if (field[x][y] != null && field[x][y].getClass() == Black.class) {
                    int deltaX = x - a;
                    int deltaY = y - b;
                    int xx = x;
                    int yy = y;
                    var tmp = new ArrayList<White>();
                    while (field[xx][yy] != null && field[xx][yy].getClass() != null) {
                        tmp.add(new White(xx, yy));
                        xx += deltaX;
                        yy += deltaY;
                        if (xx >= 8 || yy >= 8 || xx < 0 || yy < 0) {
                            if (xx >= 8) xx--;
                            if (yy >= 8) yy--;
                            if (xx < 0) xx++;
                            if (yy < 0) yy++;
                            break;
                        }
                        if (field[xx][yy] != null && field[xx][yy].getClass() == White.class)
                            break;
                    }
                    if (field[xx][yy] != null && field[xx][yy].getClass() == White.class) {
                        for (var elem : tmp) {
                            field[elem.a][elem.b] = new White(elem.a, elem.b);
                        }
                    }
                    tmp.clear();
                }
            }
        }
    }
}
