package com.dng.cs.core.exception.controller;

import com.dng.cs.core.controller.response.AuthApiResponse;
import com.dng.cs.core.exception.BusinessConstraintException;
import com.dng.cs.core.exception.EntityNotFoundException;
import com.dng.cs.core.exception.InvalidReqBodyException;
import com.dng.cs.core.exception.NoApiKeyException;
import com.dng.cs.core.exception.response.BusinessApiResponse;
import org.hibernate.JDBCException;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.PersistenceException;
import java.sql.SQLException;

@ControllerAdvice
public class AdviceController {

    @ExceptionHandler({BusinessConstraintException.class,
                       InvalidReqBodyException.class,
                       PersistenceException.class,
                       HttpMessageNotReadableException.class})
    public ResponseEntity<Object> businessConstraint(RuntimeException ex) {
        return new ResponseEntity<>(BusinessApiResponse.businessException(ex.getMessage()),
                                    HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({EntityNotFoundException.class, EmptyResultDataAccessException.class})
    public ResponseEntity<Object> entityNotFound(RuntimeException ex) {
        return new ResponseEntity<>(BusinessApiResponse.businessException(ex.getMessage()),
                                    HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> constraintError(ConstraintViolationException ex) {
        return new ResponseEntity<>(BusinessApiResponse.constraintException(ex),
                                    HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({SQLException.class, JDBCException.class})
    public ResponseEntity<Object> sqlError(SQLException ex) {
        return new ResponseEntity<>(BusinessApiResponse.sqlException(ex),
                                    HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoApiKeyException.class)
    public ResponseEntity<Object> noApiKey() {
        return new ResponseEntity<>(AuthApiResponse.noPaiKey(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}









