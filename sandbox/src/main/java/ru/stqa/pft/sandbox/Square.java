package ru.stqa.pft.sandbox;

public class Square {

  private double l;

  public Square(double l){
    this.l = l;
  }

  public double getL(){
    return l;
  }

  public double area(){
    return this.getL() * this.getL();
  }

}
