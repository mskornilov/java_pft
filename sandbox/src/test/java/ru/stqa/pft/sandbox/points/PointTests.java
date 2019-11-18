package ru.stqa.pft.sandbox.points;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

    @Test
    public void testDistance(){
        Point a = new Point(1,3);
        Point b = new Point(4, 7);
        Assert.assertEquals(a.distance(b), 5.0);
    }

    @Test
    public void testNegativeCoordinates(){
        Point a = new Point(-4, -3);
        Point b = new Point(-1, -8);
        Assert.assertEquals(a.distance(b), 5.830951894845301);
    }

    @Test
    public void testSameCoordinates(){
        Point a = new Point(4, 9);
        Point b = new Point(4, 9);
        Assert.assertEquals(a.distance(b), 0.0);
    }
}
