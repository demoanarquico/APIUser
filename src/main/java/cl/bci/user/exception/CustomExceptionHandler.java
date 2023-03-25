package cl.bci.user.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.security.core.AuthenticationException;

import cl.bci.user.dto.ErrorResponse;

@ControllerAdvice
public class CustomExceptionHandler {
	
	@ExceptionHandler( AuthenticationException.class )
    public final ResponseEntity <Object> handleUnauthorizedRequestsExceptions (Exception ex, WebRequest request) {

        ErrorResponse response = new ErrorResponse(
                LocalDateTime.now().toString(),
                HttpStatus.UNAUTHORIZED.value(),
                "UNAUTHORIZED",
                98,
                ex.getMessage(),
                request.getDescription(false)
        );


        return new ResponseEntity<>( response, HttpStatus.UNAUTHORIZED);
    }
	
	 @ExceptionHandler( NotFoundException.class )
	 public final ResponseEntity <Object> handleNotFoundExceptions ( Exception ex, WebRequest request ){

	        ErrorResponse response = new ErrorResponse(
	                LocalDateTime.now().toString(),
	                HttpStatus.NOT_FOUND.value(),
	                "NOT FOUND",
	                99,
	                ex.getMessage(),
	                request.getDescription(false)
	        );

	        return new ResponseEntity<>( response, HttpStatus.NOT_FOUND );
	 }
	 
	 @ExceptionHandler( { BadRequestException.class, MissingRequestHeaderException.class } )
	 public final ResponseEntity <Object> handleBadRequestExceptions ( Exception ex, WebRequest request ){

	        ErrorResponse response = new ErrorResponse(
	                LocalDateTime.now().toString(),
	                HttpStatus.BAD_REQUEST.value(),
	                "BAD REQUEST",
	                1,
	                ex.getMessage(),
	                request.getDescription(false)
	        );

	        return new ResponseEntity<>( response, HttpStatus.BAD_REQUEST );
	  }
	 
	 @ExceptionHandler( { InternalServerErrorException.class, Exception.class } )
	 public final ResponseEntity <Object> handleInternalServerErrorExceptions ( Exception ex, WebRequest request ){

	        ErrorResponse response = new ErrorResponse(
	                LocalDateTime.now().toString(),
	                HttpStatus.INTERNAL_SERVER_ERROR.value(),
	                "INTERNAL SERVER ERROR",
	                100,
	                ex.getMessage(),
	                request.getDescription(false)
	        );

	        return new ResponseEntity<>( response, HttpStatus.INTERNAL_SERVER_ERROR );
	  }

}
