import java.util.*;


public class Game {
    LinkedList<Chip[][]> game;
    private Chip[][] field;
    private LinkedList<Black> black_;
    private LinkedList<White> white_;

    private Scanner in;

    Game() {
        game = new LinkedList<Chip[][]>();
        field = new Chip[8][8];
        field[4][4] = new White(4, 4);
        field[3][3] = new White(3, 3);
        field[4][3] = new Black(4, 3);
        field[3][4] = new Black(3, 4);
        black_ = new LinkedList<Black>();
        white_ = new LinkedList<White>();
        black_.add(new Black(4, 3));
        white_.add(new White(3, 4));
        in = new Scanner(System.in);
    }

    public static void FieldVisualisation(Chip[][] field) {
        System.out.println("   _1_ _2_ _3_ _4_ _5_ _6_ _7_ _8_");
        for (int i = 0; i < 8; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < 8; j++) {
                if (field[i][j] != null) {
                    if (field[i][j].getClass() == Black.class) {
                        System.out.print("| x ");
                    }
                    if (field[i][j].getClass() == White.class) {
                        System.out.print("| O ");
                    }
                }
                if (field[i][j] == null) {
                    System.out.print("|   ");
                }
            }
            System.out.println("|");
            System.out.println("   ___ ___ ___ ___ ___ ___ ___ ___");
        }
    }

    public static void FieldVisualisationWithYourNextMove(Chip[][] field, List<Chip> moves) {
        System.out.println("    1   2   3   4   5   6   7   8  ");
        for (int i = 0; i < 8; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < 8; j++) {
                if (field[i][j] != null) {
                    if (field[i][j].getClass() == Black.class) {
                        System.out.print("| x ");
                    }
                    if (field[i][j].getClass() == White.class) {
                        System.out.print("| O ");
                    }
                }
                if (field[i][j] == null) {
                    boolean flag = false;
                    for (Chip ch : moves) {
                        if (ch.a == i & ch.b == j) {
                            System.out.print("| * ");
                            flag = true;
                        }
                    }
                    if (!flag) {
                        System.out.print("|   ");
                    }
                }
            }
            System.out.println("|");
            System.out.println("    _   _   _   _   _   _   _   _  ");
            System.out.print("");
        }
    }

    public void FieldOutput(Chip[][] field) {
        System.out.println("Вывести  актуальное поле? 1 (да), 2 (нет)");

        while (true) {
            int answer = -1;
            try {
                answer = Integer.parseInt(in.nextLine());
            } catch (Exception ignored) {}
            if (answer == 1) {
                FieldVisualisation(field);
                break;
            } else if (answer == 2) {
                break;
            } else {
                System.out.println("Неверный ввод, попробуйте еще раз");
            }
        }
    }

    public static boolean IsGameOverOne(Chip[][] field, boolean over) {
        boolean flag = true;
        int countb = 0, countw = 0;
        for (Chip[] line : field) {
            for (Chip ch : line) {
                if (ch == null) {
                    flag = false;
                } else if (ch.getClass() == Black.class) {
                    countb++;
                } else {
                    countw++;
                }
            }
        }
        if (flag | over) {
            System.out.println("Игра окончена счет:");
            System.out.println("Черные:" + countb + "    Белые:" + countw);
            return true;
        }
        return false;
    }

    public void PlayWithGamer() {
        LinkedList<White> white;
        LinkedList<Black> black;
        while (true) {
            FieldOutput(field);
            white = new LinkedList<White>();
            black = new LinkedList<Black>();
            System.out.println("Ход черных");
            black = new LinkedList<Black>();
            for (Chip[] line : field) {
                for (Chip chip : line) {
                    if (chip != null) {
                        if (chip.getClass() == Black.class) {
                            HashSet<Black> moves = ((Black) chip).PossibleMoves(field);
                            black.addAll(moves);
                        }
                    }
                }
            }
            if (black.size() != 0) {
                System.out.println("Ваши возможные ходы с оценкой ценности");
                int count = 0;
                for (Black bl : black) {
                    System.out.println(count + " На позиции (" + (bl.a + 1) + " " + (bl.b + 1) + ") с ценностью " + bl.AnalizeBlackMove(field));
                    count++;
                }
                System.out.println("ВВедите номер выбранного варианта");
                while (true) {
                    int answer = -1;
                    try {
                        answer = Integer.parseInt(in.nextLine());
                    } catch (Exception ignored) {}
                    if (answer >= 0 & answer < count) {
                        black.get(answer).BlackMove(field);
                        break;
                    } else {
                        System.out.println("Неверный ввод, попробуйте еще раз");
                    }
                }
            }
            if (IsGameOverOne(field, false)) {
                break;
            }
            FieldOutput(field);
            for (Chip[] line : field) {
                for (Chip chip : line) {
                    if (chip != null) {
                        if (chip.getClass() == White.class) {
                            HashSet<White> moves = ((White) chip).PosibleMoves(field);
                            white.addAll(moves);
                        }
                    }
                }
            }
            if (white.size() != 0) {
                System.out.println("Ваши возможные ходы с оценкой ценности");
                int count = 0;
                for (White wt : white) {
                    System.out.println(count + " На позиции (" + (wt.a + 1) + " " + (wt.b + 1) + ") с ценностью " + wt.AnalyzeWhiteMove(field));
                    count++;
                }
                System.out.println("ВВедите номер выбранного варианта");
                while (true) {
                    int answer = -1;
                    try {
                        answer = Integer.parseInt(in.nextLine());
                    } catch (Exception ignored) {}
                    if (answer >= 0  & answer < count) {
                        white.get(answer).WhiteMove(field);
                        break;
                    } else {
                        System.out.println("Неверный ввод, попробуйте еще раз");
                    }
                }
            }
            if (IsGameOverOne(field, black.size() == 0 & white.size() == 0)) {
                break;
            }
        }
    }

    public void PlayingWithRobotLight() {
        LinkedList<Black> black;
        while (true) {
            black = new LinkedList<Black>();
            System.out.println("Ход черных");
            for (Chip[] line : field) {
                for (Chip chip : line) {
                    if (chip != null) {
                        if (chip.getClass() == Black.class) {
                            HashSet<Black> moves = ((Black) chip).PossibleMoves(field);
                            black.addAll(moves);
                        }
                    }
                }
            }
            FieldVisualisationWithYourNextMove(field, black.stream().map(x -> (Chip)x).toList());
            if (black.size() != 0) {
                System.out.println("Ваши возможные ходы с оценкой ценности");
                int count = 0;
                for (Black bl : black) {
                    System.out.println(count + " На позиции (" + (bl.a + 1) + " " + (bl.b + 1) + ") с ценностью " + bl.AnalizeBlackMove(field));
                    count++;
                }
                System.out.println("ВВедите номер выбранного варианта");
                while (true) {
                    int answer = -1;
                    try {
                        answer = Integer.parseInt(in.nextLine());
                    } catch (Exception ignored) {}
                    if (answer >= 0 & answer < count) {
                        black.get(answer).BlackMove(field);
                        break;
                    } else {
                        System.out.println("Неверный ввод, попробуйте еще раз");
                    }
                }
            }
            if (IsGameOverOne(field, false)) {
                break;
            }
            FieldOutput(field);
            double max = -1;
            White Max = null;
            for (Chip[] line : field) {
                for (Chip chip : line) {
                    if (chip != null) {
                        if (chip.getClass() == White.class) {
                            HashSet<White> moves = ((White) chip).PosibleMoves(field);
                            for (White wt : moves) {
                                if (wt.AnalyzeWhiteMove(field) > max) {
                                    max = wt.AnalyzeWhiteMove(field);
                                    Max = wt;
                                }
                            }
                        }
                    }
                }
            }
            if (Max != null) {
                Max.WhiteMove(field);
            }
            FieldOutput(field);
            if (IsGameOverOne(field, Max == null & black.isEmpty())) {
                break;
            }
        }
    }
}