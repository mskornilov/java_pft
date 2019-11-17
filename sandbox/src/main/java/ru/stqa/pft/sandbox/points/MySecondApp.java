package ru.stqa.pft.sandbox.points;

public class MySecondApp {
    public static void main(String[] args) {
        Point a = new Point(1, 3);
        Point b = new Point(4, 7);
        System.out.println("Расстояние между точкой A" + a.toString() + " и точкой B" + b.toString() + " = " + a.distance(b));

        Point c = new Point(2, 4);
        Point d = new Point(8, 2);
        System.out.println("Расстояние между точкой C" + c.toString() + " и точкой D" + d.toString() + " = " + c.distance(d));

        Point e = new Point(15, 9);
        Point f = new Point(3, 3);
        System.out.println("Расстояние между точкой E" + e.toString() + " и точкой F" + f.toString() + " = " + e.distance(f));

        Point g = new Point(3, 3);
        Point h = new Point(3, 3);
        System.out.println("Расстояние между точкой G" + g.toString() + " и точкой H" + h.toString() + " = " + g.distance(h));
    }
}
