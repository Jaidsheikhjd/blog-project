package com.blog.blog.exceptions;


import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
  
import com.blog.blog.payloads.Apiresponse;

@RestControllerAdvice
public class globalExceptionHandler {
     
    @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<Apiresponse> resourceNotFoundExceptionHandler( ResourceNotFoundException ex) {
      String message=ex.getMessage();
      Apiresponse apiresponse=new Apiresponse(message,false);
      return new ResponseEntity<Apiresponse>(apiresponse,HttpStatus.NOT_FOUND);
  }
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String,String>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex) {
    Map<String,String> resp= new HashMap<>();

    ex.getBindingResult().getAllErrors().forEach((error)->{
        String fieldName = ((FieldError)error).getField();
        String message = error.getDefaultMessage();
        resp.put(fieldName, message);
    });

    return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ApiException.class)
  public ResponseEntity<Apiresponse> HandlerApiException( ApiException ex) {
      String message=ex.getMessage();
      Apiresponse apiresponse=new Apiresponse(message,true);
      return new ResponseEntity<Apiresponse>(apiresponse,HttpStatus.BAD_REQUEST);
  }
    
}
