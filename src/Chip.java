public class Chip {
    public int a;
    public int b;
    Chip(int x, int y){
        a=x;
        b=y;
    }

    Chip(Chip chip) {
        a = chip.a;
        b = chip.b;
    }

    @Override
    public int hashCode() {
        return Integer.parseInt(String.format("%d%d", a, b));
    }

    @Override
    public boolean equals(Object obj) {
        return a == ((Chip)obj).a && b == ((Chip)obj).b;
    }
}
