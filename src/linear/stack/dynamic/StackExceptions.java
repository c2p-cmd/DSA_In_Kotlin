package linear.stack.dynamic;

public class StackExceptions {
    public static class StackOverflowException extends Exception {
        public StackOverflowException(String message) {
            super(message);
        }

        @Override
        public String getMessage() {
            return super.getMessage();
        }

        @Override
        public void printStackTrace() {
            super.printStackTrace();
        }
    }

    public static class StackUnderflowException extends Exception {
        public StackUnderflowException(String message) {
            super(message);
        }

        @Override
        public String getMessage() {
            return super.getMessage();
        }

        @Override
        public void printStackTrace() {
            super.printStackTrace();
        }
    }
}
