import java.util.HashSet;

public class Playing_field {
    White RobotNextMove(Chip[][] field) {
        Chip[][] res = field.clone();
        White max = new White(0, 0);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (res[i][j]!=null){
                    if (res[i][j].getClass() == White.class) {
                        White step = new White(i, j);
                        HashSet<White> moves = step.PosibleMoves(field);
                        max = moves.stream().findFirst().get();
                        for (White wt : moves) {
                            if (wt.AnalyzeWhiteMove(res) > max.AnalyzeWhiteMove(res)) {
                                max = wt;
                            }
                        }
                    }
                }
            }
        }
        return max;
    }

    void RobotMove(Chip[][] field) {
        White move = RobotNextMove(field);
        move.WhiteMove(field);
    }

}
