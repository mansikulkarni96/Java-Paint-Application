/*
 * This is an illustrative example of a shape we can make. Notice the
 * shape knows how to draw itself. It also adjusts the input data to be in a better
 * format for painting (rather than calculating the width each time)
 */
package project5;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.QuadCurve2D;

/**
 *
 * @author David Sprague
 */
public class P5Curve implements P5Shape {

    public P5Curve(int x1, int y1, int x2, int y2, int x3, int y3, Color c, int thick) {
        start = new Point(x1, y1);
        end = new Point(x2, y2);
        currp = new Point(x3, y3);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
        color = c;
        thickness = thick;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(thickness));
        g2.setColor(color);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.draw(new QuadCurve2D.Float(x1, y1, x3, y3, x2, y2));
    }

    // Member variables
    private int x1, y1, x2, y2, x3, y3;
    private Color color;
    private int thickness;
    private Point start, end, currp;

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
        return currp;
    }

    @Override
    public String shapeType() {
        return ShapeFactory.P5_CURVE;
    }

    @Override
    public int thickness() {
        return thickness;
    }

    @Override
    public boolean withinShape(int mx, int my) {
        if (mx >= Math.min(x3, Math.min(x1, x2)) && mx <= Math.max(x3, Math.max(x1, x2)) && my >= Math.min(y3, Math.min(y1, y2)) && my <= Math.max(y3, Math.max(y1, y2))) {
            return true;
        } else {
            return false;
        } //To change body of generated methods, choose Tools | Templates.
    }

}
