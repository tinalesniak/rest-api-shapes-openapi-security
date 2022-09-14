package pl.kurs.java.controller.errors;

import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException exc) {
        List<ValidationErrorDto> errorDtos = exc.getAllErrors().stream()
                .map(fe -> new ValidationErrorDto((fe instanceof FieldError) ? (((FieldError) fe).getField()) : null, fe.getDefaultMessage()))
                .collect(Collectors.toList());
        return new ResponseEntity(errorDtos, HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(ShapeNotFoundException.class)
//    public ResponseEntity handleTaskNotFoundException() {
//        return new ResponseEntity("SHAPE_NOT_FOUND", HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler(RecordNotFoundException.class)
//    public final ResponseEntity handleRecordNotFoundException(RecordNotFoundException ex) {
//        return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler(ParameterIncorrectException.class)
//    public final ResponseEntity handleParameterIncorrectException(ParameterIncorrectException ex) {
//        return new ResponseEntity(new ValidationErrorDto(ex.getName(), ex.getMessage()), HttpStatus.NOT_ACCEPTABLE);
//    }

    @Value
    static class ValidationErrorDto {
        private String field;
        private String code;
    }
}