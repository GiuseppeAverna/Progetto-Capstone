package Averna.Giuseppe.Progetto.Capstone.exceptions;

import Averna.Giuseppe.Progetto.Capstone.Payloads.ErrorsResponseDTO;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice // <-- Controller specifico per gestione eccezioni
// Questa classe mi serve per centralizzare la gestione delle eccezioni
// Praticamente è come creare quindi un unico punto all'interno di tutta l'applicazione
// in cui "catturare" le eccezioni. Le eccezioni possono provenire dai controllers, dai services, o
// anche da altre parti; ma questo non importa molto perché comunque arriveranno qua.
// Questo ci permetterà di gestire ogni eccezione inviando una risposta adatta per tale
// problematica, con tanto di status code corretto.
public class ExceptionsHandler {

    @ExceptionHandler(BadRequestException.class)
    // Con questa annotazione indico che questo metodo dovrà gestire le eccezioni di tipo BadRequestException
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    public ErrorsResponseDTO handleBadRequest(BadRequestException ex){
        if(ex.getErrorsList() != null) {
            // Se c'è la lista degli errori, allora manderemo una risposta contenente la lista degli errori
            String message = ex.getErrorsList().stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". " ));
            return new ErrorsResponseDTO(message, LocalDateTime.now());

        } else {
            // Se non c'è la lista, allora mandiamo un classico payload di errore con un messaggio
            return new ErrorsResponseDTO(ex.getMessage(), LocalDateTime.now());
        }
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED) // 401
    // Con questa annotazione indico che questo metodo dovrà gestire le eccezioni di tipo NotFoundException
    public ErrorsResponseDTO handleUnauthorized(UnauthorizedException ex){
        return new ErrorsResponseDTO(ex.getMessage(), LocalDateTime.now());
    }


    @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // 404
    // Con questa annotazione indico che questo metodo dovrà gestire le eccezioni di tipo NotFoundException
    public ErrorsResponseDTO handleNotFound(NotFoundException ex){
        return new ErrorsResponseDTO(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 500
    public ErrorsResponseDTO handleGenericErrors(Exception ex){
        ex.printStackTrace(); // Non dimentichiamoci che è ESTREMAMENTE UTILE tenere traccia di chi ha causato l'errore
        return new ErrorsResponseDTO("Problema lato server! Giuro che lo risolveremo presto!", LocalDateTime.now());
    }
}