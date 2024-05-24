package Averna.Giuseppe.Progetto.Capstone.exceptions;

import Averna.Giuseppe.Progetto.Capstone.Payloads.ErrorsResponseDTO;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(BadRequestException.class)

    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    public ErrorsResponseDTO handleBadRequest(BadRequestException ex){
        if(ex.getErrorsList() != null) {

            String message = ex.getErrorsList().stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". " ));
            return new ErrorsResponseDTO(message, LocalDateTime.now());

        } else {

            return new ErrorsResponseDTO(ex.getMessage(), LocalDateTime.now());
        }
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED) // 401

    public ErrorsResponseDTO handleUnauthorized(UnauthorizedException ex){
        return new ErrorsResponseDTO(ex.getMessage(), LocalDateTime.now());
    }


    @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // 404

    public ErrorsResponseDTO handleNotFound(NotFoundException ex){
        return new ErrorsResponseDTO(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 500
    public ErrorsResponseDTO handleGenericErrors(Exception ex){
        ex.printStackTrace();
        return new ErrorsResponseDTO("Problema lato Back End", LocalDateTime.now());
    }
}