/*
 * A simple factory class enabling us to create objects of different types without
 * the canvas knowing how the data is stored. We simply provide mouse values 
 * and button states
 */
package project5;

import java.awt.Color;

/**
 *
 * @author David Sprague
 */
public class ShapeFactory {

    public static final String P5_GREEN = "Green";
    public static final String P5_RED = "Red";
    public static final String P5_BLACK = "Black";
    public static final String P5_BLUE = "Blue";
    public static final String P5_RECT = "Rectangle";
    public static final String P5_CURVE = "Curve";
    public static final String P5_LINE = "Line";
    public static final String P5_OVAL = "Oval";
    public static final String P5_IMAGE = "Image";

    public static P5Shape getShape(int x1, int y1, int x2, int y2, int x3, int y3, int thickness,
            String colorTxt, String shapeType) {
        if (shapeType == null || colorTxt == null) {
            return null;
        }
        Color color = Color.BLACK;
        if (colorTxt.equalsIgnoreCase(P5_RED)) {
            color = Color.RED;
        } else if (colorTxt.equalsIgnoreCase(P5_GREEN)) {
            color = Color.GREEN;
        } else if (colorTxt.equalsIgnoreCase(P5_BLUE)) {
            color = Color.BLUE;
        }

        if (shapeType.equalsIgnoreCase(P5_CURVE)) {
            return new P5Curve(x1, y1, x2, y2, x3, y3, color, thickness);

        } else if (shapeType.equalsIgnoreCase(P5_RECT)) {
            return new P5Rectangle(x1, y1, x2, y2, color);

        } else if (shapeType.equalsIgnoreCase(P5_OVAL)) {
            return new P5Oval(x1, y1, x2, y2, color);

        } else if (shapeType.equalsIgnoreCase(P5_LINE)) {
            return new P5Line(x1, y1, x2, y2, color, thickness);

        } else if (shapeType.equalsIgnoreCase(P5_IMAGE)) {
            return new P5Image(x1, y1, x2, y2, color);
        }

        return null;
    }
}
