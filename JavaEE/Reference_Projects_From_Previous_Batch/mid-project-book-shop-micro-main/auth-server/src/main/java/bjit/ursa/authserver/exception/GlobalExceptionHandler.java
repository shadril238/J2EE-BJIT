package bjit.ursa.authserver.exception;

import bjit.ursa.authserver.model.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({AccountAlreadyExists.class})
    public ResponseEntity<APIResponse> AccountExists(Exception ex) {
        return new ResponseEntity<>(APIResponse.builder().error_message(ex.getMessage()).build(), HttpStatus.BAD_REQUEST);
    }



    @ExceptionHandler({InvalidAuthenticationCredentials.class})
    public ResponseEntity<APIResponse> InvalidCredentials(Exception ex) {
        return new ResponseEntity<>(APIResponse.builder().error_message(ex.getMessage()).build(), HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
        public  ResponseEntity<APIResponse> handleValidationException(MethodArgumentNotValidException ex) {

        Map<String,String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error)->{
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);

        });

        APIResponse apiResponse = APIResponse.builder()
                .error_message(errors.toString())
                .build();
        return new ResponseEntity<>(apiResponse,HttpStatus.BAD_REQUEST);
        }
    }
