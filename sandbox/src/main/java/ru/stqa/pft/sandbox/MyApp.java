package ru.stqa.pft.sandbox;

public class MyApp {
	public static void main(String[] args){
		hello("world");
		hello("user");
		hello("Mikhail");

		Square square = new Square(5);
		System.out.println("Площадь квадрата со стороной " + square.getL() + " = " + square.area());

		Rectangle rectangle = new Rectangle(4, 6);
		System.out.println("Площидь прямоугольника со сторонами " + rectangle.getA() + " и " + rectangle.getB() + " = " +
											rectangle.area());
	}

	public static void hello(String somebody) {
		System.out.println("Hello, " + somebody + "!");
	}
}