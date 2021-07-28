package dev.coralwombat.appointment.booking.clientservice.exception;

public class AppointmentBookingException extends Exception {

    private static final long serialVersionUID = 3871458511782915992L;

    public AppointmentBookingException(String message, Exception cause) {
	super(message, cause);
    }

}
