import java.util.ArrayList;
import java.util.List;

/**
 * Created by cullycross on 5/4/15.
 */
public class MementoPattern {

    public static void main(String[] args) {
        List<IntegerNumber.NumberProcessor> savedStates = new ArrayList<IntegerNumber.NumberProcessor>();
        IntegerNumber integerNumber = new IntegerNumber();

        System.out.println("Execution: " + integerNumber.execute(22));
        System.out.println("Execution: " + integerNumber.redo());
        System.out.println("Execution: " + integerNumber.execute(new IntegerNumber.Decrement(), 4));
        savedStates.add(integerNumber.saveToMemento());

        System.out.println("Execution: " + integerNumber.execute(new IntegerNumber.Multiplication(), 2));
        System.out.println("Execution: " + integerNumber.redo());
        System.out.println("Execution: " + integerNumber.execute(new IntegerNumber.Division(), 10));
        System.out.println("Execution: " + integerNumber.undo(savedStates.get(savedStates.size() - 1)));
        System.out.println("Execution: " + integerNumber.redo());
    }
}

class IntegerNumber {

    int mValue = 0;
    Command mExecution = new Increment();

    NumberProcessor last;

    //execute default command
    int execute(int value) {
        last = new NumberProcessor(mExecution, mValue, value);
        return mValue = mExecution.execute(mValue, value);
    }

    //change command and execute
    int execute(Command command, int value) {
        mExecution = command;
        last = new NumberProcessor(mExecution, mValue, value);
        return mValue = mExecution.execute(mValue, value);
    }

    NumberProcessor saveToMemento() {
        System.out.println("Saving to Memento.");
        return new NumberProcessor(this.mExecution, this.mValue);
    }

    int undo(NumberProcessor memento) {
        this.mValue = memento.getSavedValue();
        this.mExecution = memento.getSavedCommand();
        System.out.println("Number after restoring from Memento: " + mValue +
                "\nLast operation was: " + mExecution.getClass().getName());

        return mValue;
    }

    int redo() {
        System.out.println("Redoing: " + mValue + " and " + last.getSecondValue() +
                "\n>>Last operation was: " + mExecution.getClass().getName());

        return execute(mExecution, last.getSecondValue());
    }

    static class NumberProcessor {
        private final int mSavedValue;
        private final Command mSavedCommand;
        private final int mSecondValue;

        public NumberProcessor(Command savedCommand, int savedValue) {
            this(savedCommand, savedValue, 0);
        }

        public NumberProcessor(Command savedCommand, int savedValue ,int secondValue) {
            mSavedValue = savedValue;
            mSavedCommand = savedCommand;
            mSecondValue = secondValue;
        }

        public int getSavedValue() {
            return mSavedValue;
        }

        public Command getSavedCommand() {
            return mSavedCommand;
        }

        public int getSecondValue() {
            return mSecondValue;
        }
    }

    interface Command {
        int execute(int first, int second);
    }

    static class Increment implements Command {
        @Override
        public int execute(int first, int second) {
            return first + second;
        }
    }

    static class Decrement implements Command {

        @Override
        public int execute(int first, int second) {
            return first - second;
        }
    }

    static class Multiplication implements Command {
        @Override
        public int execute(int first, int second) {
            return first * second;
        }
    }

    static class Division implements Command {
        @Override
        public int execute(int first, int second) {
            return first / second;
        }
    }
}
