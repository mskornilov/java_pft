package ru.stqa.pft.sandbox.points;

public class Point {

    private double x;
    private double y;

    Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "(x = " + x + ", " + "y = " + y + ")";
    }

    private double getX(){
        return x;
    }

    private double getY(){
        return y;
    }

    public  double distance(Point p2){
        // расчитываем расстояние между точками, принимая отрезок между ними за гипотенузу прямоугольного треугольника
        double side1 = Math.abs(this.getX() - p2.getX());
        double side2 = Math.abs(this.getY() - p2.getY());
        return Math.sqrt(Math.pow(side1, 2) + Math.pow(side2, 2));
    }
}
