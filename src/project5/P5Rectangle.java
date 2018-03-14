/*
 * This is an illustrative example of a shape we can make. Notice the
 * shape knows how to draw itself. It also adjusts the input data to be in a better
 * format for painting (rather than calculating the width each time)
 */
package project5;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author David Sprague
 */
public class P5Rectangle implements P5Shape {

    public P5Rectangle(int x1, int y1, int x2, int y2, Color c) {
        start = new Point(x1, y1);
        end = new Point(x2, y2);
        if (x1 < x2) {
            x = x1;
            width = x2 - x1;
        } else {
            x = x2;
            width = x1 - x2;
        }
        if (y1 < y2) {
            y = y1;
            height = y2 - y1;
        } else {
            y = y2;
            height = y1 - y2;
        }
        color = c;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }

    // Member variables
    private int x, y, width, height;
    private Color color;
    private Point start;
    private Point end;

    @Override
    public Color color() {
        return color;
    }

    @Override
    public Point startingPoint() {
        return start;
    }

    @Override
    public Point endPoint() {
        return end;
    }

    @Override
    public Point currPoint() {
        return null;
    }

    @Override
    public String shapeType() {
        return ShapeFactory.P5_RECT;
    }

    @Override
    public int thickness() {
        return 0;
    }

    @Override
    public boolean withinShape(int mx, int my) {
        if (mx >= x && mx <= x + width && my >= y && my <= y + height) {
            return true;
        } else {
            return false;
        }
    }
    //To change body of generated methods, choose Tools | Templates.
}
