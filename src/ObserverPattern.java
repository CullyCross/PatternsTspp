import java.util.ArrayList;
import java.util.List;

/**
 * Created by cullycross on 5/4/15.
 */
public class ObserverPattern {

    public static void main(String[] args) {

        Paranoid paranoid = new Paranoid();

        paranoid.attachObserver(new Spy());
        paranoid.attachObserver(new Maniac());

        paranoid.turnBack();
        paranoid.runAway();

    }

    interface Observable {
        void attachObserver(Observer observer);
        void detachObsever(Observer observer);
        void notifyObservers(State state);
    }

    interface Observer {
        void onNotify(State state);
    }

    static class Paranoid implements Observable {

        private List<Observer> mObservers = new ArrayList<Observer>();
        private State mState = State.WALK;

        void turnBack() {
            System.out.println("Paranoid turns back");
            mState = State.TURN_BACK;
            notifyObservers(mState);
        }

        void runAway() {
            System.out.println("Paranoid runs away");
            mState = State.RUN_AWAY;
            notifyObservers(mState);
        }

        @Override
        public void attachObserver(Observer observer) {
            mObservers.add(observer);
        }

        @Override
        public void detachObsever(Observer observer) {
            mObservers.remove(observer);
        }

        @Override
        public void notifyObservers(State state) {
            for(Observer o : mObservers) {
                o.onNotify(state);
            }
        }
    }

    static class Spy implements Observer {

        @Override
        public void onNotify(State state) {

            switch (state) {
                case TURN_BACK:
                    System.out.println("Spy calls to the headquarters");
                    break;
                case RUN_AWAY:
                    System.out.println("Spy tries to shot");
                    break;
                default:
                    break;
            }
        }
    }

    static class Maniac implements Observer {

        @Override
        public void onNotify(State state) {

            switch (state) {
                case TURN_BACK:
                    System.out.println("Maniac smiles");
                    break;
                case RUN_AWAY:
                    System.out.println("Maniac runs with a big axe");
                    break;
                default:
                    break;
            }
        }
    }

    enum State {
        TURN_BACK, RUN_AWAY, WALK
    }

}
