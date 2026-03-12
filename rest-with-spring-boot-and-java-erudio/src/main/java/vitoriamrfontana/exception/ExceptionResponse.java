package vitoriamrfontana.exception;

import java.util.Date;

public record ExceptionResponse(Date timesTamp, String message, String details) {
}
