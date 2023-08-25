public class TestCircleSquareDep {
    public static void main(String[] args) {
        System.out.println(SquareMath.y);
        System.out.println(CircleMath.x);
        System.out.println(SquareMath.y);

    }
}

class CircleMath{
    public static final double x = SquareMath.y + 10;
}

class SquareMath{
    public static final double y = CircleMath.x + 10;
}
