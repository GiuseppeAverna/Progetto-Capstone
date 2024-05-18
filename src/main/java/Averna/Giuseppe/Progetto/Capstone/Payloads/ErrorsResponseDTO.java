package Averna.Giuseppe.Progetto.Capstone.Payloads;

import java.time.LocalDateTime;

public record ErrorsResponseDTO(String message, LocalDateTime timestamp) {}