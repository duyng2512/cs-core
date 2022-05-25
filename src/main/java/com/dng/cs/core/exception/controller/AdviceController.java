package com.dng.cs.core.exception.controller;

import com.dng.cs.core.exception.BusinessConstraintException;
import com.dng.cs.core.exception.EntityNotFoundException;
import com.dng.cs.core.exception.InvalidReqBodyException;
import com.dng.cs.core.exception.response.BusinessApiResponse;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AdviceController {

    @ExceptionHandler({BusinessConstraintException.class, InvalidReqBodyException.class})
    public ResponseEntity<Object> businessConstraint(RuntimeException ex) {
        return new ResponseEntity<>(BusinessApiResponse.exception(ex.getMessage()),
                                    HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({EntityNotFoundException.class, EmptyResultDataAccessException.class})
    public ResponseEntity<Object> entityNotFound(RuntimeException ex) {
        return new ResponseEntity<>(BusinessApiResponse.exception(ex.getMessage()),
                                    HttpStatus.NOT_FOUND);
    }

}
