//package com.nhnacademy.projectapi.advice;
//
//import com.nhnacademy.projectapi.exception.NotFoundException;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.WebDataBinder;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.InitBinder;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//
//@Slf4j
//@RestControllerAdvice
//public class CommonAdvice extends ResponseEntityExceptionHandler {
//
//    @InitBinder
//    void initBinder(WebDataBinder binder){
//        binder.initDirectFieldAccess();
//    }
//
//    @ExceptionHandler(NotFoundException.class)
//    @ResponseStatus(value = HttpStatus.NOT_FOUND)
//    public ResponseEntity<Object> notFoundException(NotFoundException e) {
//
//        return ResponseEntity.notFound().build();
//    }
//}
