/**
 * Created by cullycross on 5/4/15.
 */
public class AdapterPattern {

    public static void main(String... args) {

    }

    class Shape {

        Shape() {

        }

        void draw() {

        }

        interface drawable {
            draw();
        }
    }

    class Triangle {
        void drawTriangle() {
            System.out.println("Drawing triangle");
        }
    }

    class Circle {
        void drawCircle() {
            System.out.println("Drawing circle");
        }
    }

    class Square {
        void drawSquare() {
            System.out.println("Drawing square");
        }
    }
}
