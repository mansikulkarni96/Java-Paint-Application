/*
 * This is an illustrative example of a shape we can make. Notice the
 * shape knows how to draw itself. It also adjusts the input data to be in a better
 * format for painting (rather than calculating the width each time)
 */
package project5;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Line2D;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Point;
import java.awt.RenderingHints;

/**
 *
 * @author David Sprague
 */
public class P5Line implements P5Shape {

    public P5Line(int x1, int y1, int x2, int y2, Color c, int thick) {
        start = new Point(x1, y1);
        end = new Point(x2, y2);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        color = c;
        thickness = thick;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(thickness));
        g2.setColor(color);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.draw(new Line2D.Float(x1, y1, x2, y2));

    }

    // Member variables
    private int x1, y1, x2, y2;
    private Color color;
    private int thickness;
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
        return ShapeFactory.P5_LINE;
    }

    @Override
    public int thickness() {
        return thickness;
    }

    @Override
    public boolean withinShape(int mx, int my) {
        if (mx >= Math.min(x1, x2) && mx <= Math.max(x1, x2) && my >= Math.min(y1, y2) && my <= Math.max(y1, y2)) {
            return true;
        } else {
            return false;
        }
        //To change body of generated methods, choose Tools | Templates.
    }
}
