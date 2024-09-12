package hnqd.project.ApartmentManagement.exceptions;

public class CommonException{
    public static class NotFoundException extends BaseException {
        public NotFoundException(String msg) {
            super(msg);
        }
    }

    public static class WrongPasswordException extends BaseException {
        public WrongPasswordException(String msg) {
            super(msg);
        }
    }

    public static class DuplicatePasswordException extends BaseException {
        public DuplicatePasswordException(String msg) {
            super(msg);
        }
    }
}
