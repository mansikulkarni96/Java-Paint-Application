/*
 * This is the main drawing class for the project.  A P5Canvas object should
 * be able how to draw all lines, rectangles and ovals indicated by the user
 * each time the paint method is called.
 */
package project5;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import static project5.P5CanvasController.presscnt;

/**
 *
 * @author David Sprague
 */
public class P5Canvas extends JPanel {

    public P5Canvas() {
        this.shapeList = new ArrayList();
        mouseStart = null;
        mouseEnd = null;
        color = ShapeFactory.P5_BLACK;
        shape = ShapeFactory.P5_RECT;
        thickness = 1;
    }

    public void setColor(String col) {
        color = col;
    }

    public void setThickness(int thick) {
        thickness = thick;
    }

    public void startDrag(Point p) {
        mouseStart = p;
    }

    // Note: You need additional set methods for thickness and color
    public void setShape(String shape) {
        this.shape = shape;
    }

    public void endDrag(Point p) {
        if (!shape.equalsIgnoreCase(ShapeFactory.P5_CURVE)) {
            P5Shape newShape = ShapeFactory.getShape(mouseStart.x, mouseStart.y,
                    p.x, p.y, 0, 0, thickness, color, shape);
            P5CanvasController.presscnt = 0;
            if (newShape != null) {
                shapeList.add(newShape);
            }
            repaint();
        } else {
            mouseEnd = p;
            P5Shape newShape = ShapeFactory.getShape(mouseStart.x, mouseStart.y,
                    p.x, p.y, 0, 0, thickness, color, ShapeFactory.P5_LINE);
            if (newShape != null) {
                shapeList.add(newShape);
            }
            repaint();
        }
    }

    public P5Shape selected(P5Shape shape) {
        P5Shape newShape = null;
        String colorTxt = null;
        if (shape.color().equals(Color.BLUE)) {
            colorTxt = ShapeFactory.P5_RED;
        } else if (shape.color().equals(Color.RED)) {
            colorTxt = ShapeFactory.P5_BLACK;
        } else if (shape.color().equals(Color.GREEN)) {
            colorTxt = ShapeFactory.P5_RED;
        } else if (shape.color().equals(Color.BLACK)) {
            colorTxt = ShapeFactory.P5_RED;
        }
        if (shape.currPoint() == null) {
            newShape = ShapeFactory.getShape((int) shape.startingPoint().getX(), (int) shape.startingPoint().getY(),
                    (int) shape.endPoint().getX(), (int) shape.endPoint().getY(), 0, 0, shape.thickness(), colorTxt, shape.shapeType());
        } else {
            newShape = ShapeFactory.getShape((int) shape.startingPoint().getX(), (int) shape.startingPoint().getY(),
                    (int) shape.endPoint().getX(), (int) shape.endPoint().getY(), (int) shape.currPoint().getX(), (int) shape.currPoint().getY(), shape.thickness(), colorTxt, shape.shapeType());
        }
        if (newShape != null) {
            shapeList.add(newShape);
            if (newShape.shapeType().equals(ShapeFactory.P5_IMAGE)) {
                P5Image p = (P5Image) newShape;
                P5Image p1 = (P5Image) shape;
                p.setImage(p1.getImage());
                System.out.println(p.getImage());
                System.out.println(shape.shapeType());
            }
        }
        System.out.println(shapeList);
        repaint();
        return newShape;
    }

    public void revert(P5Shape shape, Color originalColor) {
        P5Shape newShape = null;
        String colorTxt = null;
        if (originalColor.equals(Color.BLUE)) {
            colorTxt = ShapeFactory.P5_BLUE;
        } else if (originalColor.equals(Color.RED)) {
            colorTxt = ShapeFactory.P5_RED;
        } else if (originalColor.equals(Color.GREEN)) {
            colorTxt = ShapeFactory.P5_GREEN;
        } else if (originalColor.equals(Color.BLACK)) {
            colorTxt = ShapeFactory.P5_BLACK;
        }
        if (shape.currPoint() == null) {
            newShape = ShapeFactory.getShape((int) shape.startingPoint().getX(), (int) shape.startingPoint().getY(),
                    (int) shape.endPoint().getX(), (int) shape.endPoint().getY(), 0, 0, shape.thickness(), colorTxt, shape.shapeType());
        } else {
            newShape = ShapeFactory.getShape((int) shape.startingPoint().getX(), (int) shape.startingPoint().getY(),
                    (int) shape.endPoint().getX(), (int) shape.endPoint().getY(), (int) shape.currPoint().getX(), (int) shape.currPoint().getY(), shape.thickness(), colorTxt, shape.shapeType());
        }
        if (newShape != null) {
            shapeList.add(newShape);
            if (newShape.shapeType().equals(ShapeFactory.P5_IMAGE)) {
                P5Image p = (P5Image) newShape;
                P5Image p1 = (P5Image) shape;
                p.setImage(p1.getImage());
                System.out.println(p.getImage());
                System.out.println(shape.shapeType());
            }
        }
        repaint();
    }

    public P5Shape drag(Point start, Point end, Point curr, Color color, P5Shape shape) {

        P5Shape newShape = null;
        String colorTxt = null;
        if (color.equals(Color.BLUE)) {
            colorTxt = ShapeFactory.P5_BLUE;
        } else if (color.equals(Color.RED)) {
            colorTxt = ShapeFactory.P5_RED;
        } else if (color.equals(Color.GREEN)) {
            colorTxt = ShapeFactory.P5_GREEN;
        } else if (color.equals(Color.BLACK)) {
            colorTxt = ShapeFactory.P5_BLACK;
        }
        if (shape.currPoint() == null) {
            newShape = ShapeFactory.getShape((int) start.getX(), (int) start.getY(),
                    (int) end.getX(), (int) end.getY(), 0, 0, shape.thickness(), colorTxt, shape.shapeType());
        } else {
            newShape = ShapeFactory.getShape((int) start.getX(), (int) start.getY(),
                    (int) end.getX(), (int) end.getY(), (int) curr.getX(), (int) curr.getY(), shape.thickness(), colorTxt, shape.shapeType());
        }
        if (newShape != null) {
            shapeList.add(newShape);
            if (newShape.shapeType().equals(ShapeFactory.P5_IMAGE)) {
                P5Image p = (P5Image) newShape;
                P5Image p1 = (P5Image) shape;
                p.setImage(p1.getImage());
                System.out.println(p.getImage());
                System.out.println(shape.shapeType());
            }
        }
        repaint();
        return newShape;
    }

    public void currentDrag(Point curr) {
        mouseCurr = curr;
        P5Shape newShape = ShapeFactory.getShape(mouseStart.x, mouseStart.y,
                mouseEnd.x, mouseEnd.y, mouseCurr.x, mouseCurr.y, thickness, color, shape);
        P5CanvasController.presscnt = 0;
        if (newShape != null) {
            P5Shape x = shapeList.get(shapeList.size() - 1);
            shapeList.remove(shapeList.size() - 1);
            shapeList.add(newShape);

            if (newShape.shapeType().equals(ShapeFactory.P5_IMAGE)) {
                P5Image p = (P5Image) newShape;
                P5Image p1 = (P5Image) x;
                p.setImage(p1.getImage());
            }
            repaint();
        }
    }

    public void save() {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter fl = new FileNameExtensionFilter("Text files", "txt");
        chooser.setFileFilter(fl);
        int returnVal = chooser.showSaveDialog(null);
        try {
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(chooser.getSelectedFile()));
                oos.writeObject(shapeList);
                oos.close();
            } else if (returnVal == JFileChooser.CANCEL_OPTION) {
                System.out.println("No File is Selected");
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Invalid File! ", "error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error in writing a file! ", "error", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void load() {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter fl = new FileNameExtensionFilter("Text files", "txt");
        chooser.setFileFilter(fl);
        int returnVal = chooser.showOpenDialog(null);
        try {
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                ObjectInputStream in = new ObjectInputStream(new FileInputStream(chooser.getSelectedFile()));
                this.shapeList = (ArrayList<P5Shape>) in.readObject();
                repaint();
                in.close();
            } else if (returnVal == JFileChooser.CANCEL_OPTION) {
                System.out.println("No File is Selected");
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Invalid File! ", "error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error in writing a file! ", "error", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Class not found! ", "error", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void clearCanvas() {
        shapeList.clear();
        repaint();
    }

    public void addShape(P5Shape shape) {
        if (shape != null) {
            shapeList.add(shape);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        // Paint each of the paint objects
        for (P5Shape shape : shapeList) {
            shape.paint(g);
        }
    }

    // Member Variables
    ArrayList<P5Shape> shapeList;
    Point mouseStart;
    Point mouseEnd;
    Point mouseCurr;
    int thickness;
    String color;
    String shape;

    void loadImage() {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter fl = new FileNameExtensionFilter("Image files", "jpg");
        chooser.setFileFilter(fl);
        int returnVal = chooser.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {

            P5Image i = new P5Image(0, 0, 200, 200, Color.RED);
            i.setImage(chooser.getSelectedFile().getAbsolutePath());
            this.shapeList.add(i);
            repaint();
            // in.close();
        } else if (returnVal == JFileChooser.CANCEL_OPTION) {
            System.out.println("No File is Selected");
        }
        //To change body of generated methods, choose Tools | Templates.
    }

}
