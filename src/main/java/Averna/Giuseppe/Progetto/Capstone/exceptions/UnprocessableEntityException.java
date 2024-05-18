package Averna.Giuseppe.Progetto.Capstone.exceptions;

import lombok.Getter;
import org.springframework.validation.ObjectError;

import java.util.List;

@Getter
public class UnprocessableEntityException extends RuntimeException {
    private List<ObjectError> errorsList;

    public UnprocessableEntityException(String message) {
        super(message);
    }

    public UnprocessableEntityException(List<ObjectError> errorsList) {
        super("Errori nel body");
        this.errorsList = errorsList;
    }
}
