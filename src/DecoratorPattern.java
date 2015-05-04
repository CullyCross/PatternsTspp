import java.util.ArrayList;
import java.util.List;

/**
 * Created by cullycross on 5/4/15.
 */
public class DecoratorPattern {

    public static void main(String... args) {

        Window decoratedWindow = new HorizontalScrollBarDecorator (
            new VerticalScrollBarDecorator(
                new TitleBarDecorator (
                        new SimpleWindow()
                )
            )
        );

        decoratedWindow.draw();
    }

    interface Window {
        void draw(); // Draws the Window
    }

    static class SimpleWindow implements Window {
        @Override
        public void draw() {
            System.out.println("Drawing SimpleWindow");
        }
    }

    abstract static class WindowDecorator implements Window {

        protected Window windowToBeDecorated; // the Window being decorated

        WindowDecorator (Window windowToBeDecorated) {
            this.windowToBeDecorated = windowToBeDecorated;
        }

        @Override
        public void draw() {
            windowToBeDecorated.draw(); //Delegation
        }
    }

    static class TitleBarDecorator extends WindowDecorator {

        private List<Button> mButtons;

        public TitleBarDecorator (Window windowToBeDecorated) {
            super(windowToBeDecorated);

            mButtons = new ArrayList<Button>();
            mButtons.add(new Button() {
                @Override
                void draw() {
                    System.out.println("Draw close button");
                }
            });

            mButtons.add(new Button() {
                @Override
                void draw() {
                    System.out.println("Draw minimize button");
                }
            });

            mButtons.add(new Button() {
                @Override
                void draw() {
                    System.out.println("Draw maximize button");
                }
            });
        }

        @Override
        public void draw() {
            super.draw();
            drawTitleBar();

            for (Button b : mButtons) {
                b.draw();
            }
        }

        private void drawTitleBar() {
            System.out.println("Draw title bar");
        }
    }

    static class VerticalScrollBarDecorator extends WindowDecorator {
        public VerticalScrollBarDecorator (Window windowToBeDecorated) {
            super(windowToBeDecorated);
        }

        @Override
        public void draw() {
            super.draw();
            drawVerticalScrollBar();
        }

        private void drawVerticalScrollBar() {
            System.out.println("Draw vertical scroll bar");
        }
    }

    static class HorizontalScrollBarDecorator extends WindowDecorator {
        public HorizontalScrollBarDecorator (Window windowToBeDecorated) {
            super(windowToBeDecorated);
        }

        @Override
        public void draw() {
            super.draw();
            drawHorizontalScrollBar();
        }

        private void drawHorizontalScrollBar() {
            System.out.println("Draw horizontal scroll bar");
        }
    }

    abstract static class Button {
        abstract void draw();
    }
}
