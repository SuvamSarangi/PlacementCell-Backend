package com.example.placementcell.Exception;

import com.example.placementcell.Payload.ApiResponce;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)  //Which type of exception pass
    public ResponseEntity<ApiResponce> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){

        String message = ex.getMessage();
        ApiResponce apiResponce= new ApiResponce(message,false);
        return new ResponseEntity<ApiResponce>(apiResponce, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>>handleMethotArgsNotValidException(MethodArgumentNotValidException ex){
        Map<String,String> resp = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error)->{
            String fieldName = ((FieldError)error).getField();
            String message = error.getDefaultMessage();
            resp.put(fieldName,message);
        });
        return new ResponseEntity<Map<String,String>>(resp,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ApiException.class)  //Which type of exception pass
    public ResponseEntity<ApiResponce> handleApiException(ApiException ex){

        String message = ex.getMessage();
        ApiResponce apiResponce= new ApiResponce(message,true);
        return new ResponseEntity<ApiResponce>(apiResponce, HttpStatus.BAD_REQUEST);
    }
}
