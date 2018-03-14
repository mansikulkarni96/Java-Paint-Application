/*
 * This interface is used for all shapes drawn on a P5Canvas object. Arc, Rectangle
 * and Line classes should implement all of these methods and thus the shape type
 * handles complexity associated with how the shapes are modified and drawn
 */
package project5;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Point;
import java.io.Serializable;

/**
 *
 * @author David Sprague
 */
public interface P5Shape extends Serializable {

    public static final long serialVersionUID = 1L;

    public void paint(Graphics g);

    public boolean withinShape(int mx, int my);

    public Color color();

    public Point startingPoint();

    public Point endPoint();

    public Point currPoint();

    public String shapeType();

    public int thickness();
}
