package br.ueg.progweb2.arquitetura.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiRestResponseExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ BusinessLogicException.class })
    public ResponseEntity<Object> handleBusinessException(final BusinessLogicException e) {
        return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED)
                .body("Erro:" + e.getMessage());
    }

    @ExceptionHandler({ MandatoryException.class })
    public ResponseEntity<Object> handleMandaToryException(final MandatoryException e) {
        return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED)
                .body("Erro:" + e.getMessage());
    }

    @ExceptionHandler({ DataException.class })
    public ResponseEntity<Object> handleDataException(final DataException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Erro:"+e.getMessage());
    }

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> handleException(final Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Erro:"+e.getMessage());
    }


}