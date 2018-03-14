/*
 * This is an illustrative example of a shape we can make. Notice the
 * shape knows how to draw itself. It also adjusts the input data to be in a better
 * format for painting (rather than calculating the width each time)
 */
package project5;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author David Sprague
 */
public class P5Image implements P5Shape {

    public P5Image(int x1, int y1, int x2, int y2, Color c) {
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

    P5Image(int i, int i0, int i1, int i2, String P5_BLUE) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void paint(Graphics g) {
        ImageIcon icon = new ImageIcon(this.img);
        Image im = icon.getImage();
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(im, x, y, width, height, null);

    }

    // Member variables
    private int x, y, width, height;
    private Color color;
    private Point start;
    private Point end;
    private String img;

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
        return ShapeFactory.P5_IMAGE;
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
        }//To change body of generated methods, choose Tools | Templates.
    }

    public void setImage(String img) {
        this.img = img;
    }

    public String getImage() {
        return this.img;
    }
}
