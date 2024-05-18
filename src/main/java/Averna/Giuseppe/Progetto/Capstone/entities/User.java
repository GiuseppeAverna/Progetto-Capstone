package Averna.Giuseppe.Progetto.Capstone.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class User {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String avatarURL;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private Cart cart;

    public User(String name, String surname, String email, String password, String avatarURL) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.avatarURL = avatarURL;
    }
}