/**
 * Created by cullycross on 5/4/15.
 */
public class ProxyPattern {

    public static void main(String[] args) {
        final Person IMAGE1 = new ProxyPerson("Caroline");
        final Person IMAGE2 = new ProxyPerson("Valerie");

        IMAGE1.displayName();
        IMAGE1.displayName();
        IMAGE2.displayName();
        IMAGE2.displayName();
        IMAGE1.displayName();
    }

    interface Person {
        public String displayName();
    }

    static class RealPerson implements Person {

        private String mName = null;

        public RealPerson(final String name) {
            this.mName = name;
            loadPerson();
        }

        private void loadPerson() {
            System.out.println("Loading   " + mName);
        }

        public String displayName() {
            System.out.println("Displaying " + mName);
            return mName;
        }

    }

    static class ProxyPerson implements Person {

        private RealPerson mPerson = null;
        private String mName = null;

        public ProxyPerson(final String name) {
            this.mName = name;
        }

        public String displayName() {
            if (mPerson == null) {
                mPerson = new RealPerson(mName);
            }
            mName = new StringBuilder(mPerson.displayName()).reverse().toString();
            System.out.println("Displaying " + mName);
            return mName;
        }

    }
}
