/*
 * The controller class for the drawing canvas. Note this needs access
 * to the model/view
 */
package project5;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * An implementation of MouseAdapter so you can handle any mouse events you
 * wish.
 *
 * @author David Sprague
 */
public class P5CanvasController extends MouseAdapter {

    public static int presscnt = 0;
    public Point oldp = new Point();
    public Point newp = new Point();
    public Point curp = new Point();
    boolean draggable = false;
    P5Shape selectShape = null;
    Color originalColor = null;

    public Point drag1 = null;
    public Point drag2 = null;
    public Point drag3 = null;

    public boolean mouseExit = false;

    public P5CanvasController(P5Canvas c) {
        canvas = c;
    }

    @Override
    public void mousePressed(MouseEvent me) {
        if (presscnt == 0) {
            if (canvas.shapeList != null) {
                for (P5Shape shape : canvas.shapeList) {
                    draggable = shape.withinShape(me.getX(), me.getY());
                    if (draggable) {
                        canvas.shapeList.remove(shape);
                        this.originalColor = shape.color();
                        this.selectShape = canvas.selected(shape);
                        int x1 = (int) (me.getX() - selectShape.startingPoint().getX());
                        int y1 = (int) (me.getY() - selectShape.startingPoint().getY());
                        drag1 = new Point(x1, y1);
                        int x2 = (int) (me.getX() - selectShape.endPoint().getX());
                        int y2 = (int) (me.getY() - selectShape.endPoint().getY());
                        drag2 = new Point(x2, y2);
                        if (selectShape.shapeType().equals(ShapeFactory.P5_CURVE)) {
                            int x3 = (int) (me.getX() - selectShape.currPoint().getX());
                            int y3 = (int) (me.getY() - selectShape.currPoint().getY());
                            drag3 = new Point(x3, y3);
                        }
                        break;
                    }
                }
                if (!draggable) {
                    oldp = me.getPoint();
                    canvas.startDrag(oldp);
                    presscnt++;
                }
            } else {
                oldp = me.getPoint();
                canvas.startDrag(oldp);
                presscnt++;
            }
        } else if (presscnt == 1) {
            presscnt = 0;
        }

    }

    @Override
    public void mouseReleased(MouseEvent me) {
        if (!draggable) {
            if (presscnt == 1) {
                newp = me.getPoint();
                canvas.endDrag(newp);
            } else if (presscnt == 0 && !mouseExit) {
                curp = me.getPoint();
                canvas.currentDrag(curp);
            }
        } else {
            draggable = false;
            canvas.shapeList.remove(selectShape);
            canvas.revert(selectShape, originalColor);
        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        mouseExit = false;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (draggable) {
            draggable = false;
            mouseExit = true;
            canvas.shapeList.remove(selectShape);
            canvas.revert(selectShape, originalColor);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (draggable) {
            canvas.shapeList.remove(selectShape);
            int x1 = (int) (e.getX() - drag1.getX());
            int y1 = (int) (e.getY() - drag1.getY());
            Point start = new Point(x1, y1);
            int x2 = (int) (e.getX() - drag2.getX());
            int y2 = (int) (e.getY() - drag2.getY());
            Point end = new Point(x2, y2);
            Point currp = null;
            if (drag3 != null) {
                int x3 = (int) (e.getX() - drag3.getX());
                int y3 = (int) (e.getY() - drag3.getY());
                currp = new Point(x3, y3);
            }
            selectShape = canvas.drag(start, end, currp, selectShape.color(), selectShape);
        }
    }

    // Member Variables
    P5Canvas canvas;

}
