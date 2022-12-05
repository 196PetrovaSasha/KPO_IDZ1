import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        while (true) {
            System.out.println("Начнем играть");
            System.out.println("Введите номер выбранного режима игры");
            System.out.println("1. Легкий режим");
            System.out.println("2. Игрок против игрока");
            Game g = new Game();
            Scanner in = new Scanner(System.in);
            while (true) {
                int answer = -1;
                try {
                    answer = Integer.parseInt(in.nextLine());
                } catch (Exception ignored) {
                }
                if (answer == 1) {
                    g.PlayingWithRobotLight();
                    break;
                } else if (answer == 2) {
                    g.PlayWithGamer();
                    break;
                } else {
                    System.out.println("неверный ввод, попробуйте еще");
                }
            }
            System.out.println("Хотите ли сыграть еще раз? y (да) n (нет)");
            boolean flag = true;
            while (true) {
                String answer = in.nextLine();
                if (Objects.equals(answer, "n")) {
                    flag = false;
                    break;
                } else if (Objects.equals(answer, "y")){
                    break;
                }else {
                    System.out.println("неверный ввод, попробуйте еще");
                }
            }
            if (!flag){
                break;
            }
        }
    }
}