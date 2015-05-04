/**
 * Created by cullycross on 5/4/15.
 */
public class AdapterPattern {

    public static void main(String... args) {

        final Triangle triangle = new Triangle();
        final Square square = new Square();
        final Circle circle = new Circle();

        Shape newTriangle = new Shape(new Shape.Drawable() {
            @Override
            public void draw() {
                triangle.drawTriangle();
            }
        });

        Shape newSquare = new Shape(new Shape.Drawable() {
            @Override
            public void draw() {
                square.drawSquare();
            }
        });

        Shape newCircle = new Shape(new Shape.Drawable() {
            @Override
            public void draw() {
                circle.drawCircle();
            }
        });

        newTriangle.draw();
        newSquare.draw();
        newCircle.draw();

    }



    static class Triangle {
        void drawTriangle() {
            System.out.println("Drawing triangle");
        }
    }

    static class Circle {
        void drawCircle() {
            System.out.println("Drawing circle");
        }
    }

    static class Square {
        void drawSquare() {
            System.out.println("Drawing square");
        }
    }
}

class Shape {

    private Drawable mShape;

    Shape(Drawable shape) {
        mShape = shape;
    }

    void draw() {
        mShape.draw();
    }

    interface Drawable {
        void draw();
    }
}
