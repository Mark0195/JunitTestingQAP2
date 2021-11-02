package QAP.Manager;

public class InvalidDonationSchedulingException extends Exception{
    public InvalidDonationSchedulingException() {
        super();
    }

    public InvalidDonationSchedulingException(String message) {
        super(message);
    }
}