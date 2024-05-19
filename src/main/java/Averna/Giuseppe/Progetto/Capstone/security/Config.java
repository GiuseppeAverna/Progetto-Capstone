package Averna.Giuseppe.Progetto.Capstone.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
// Se voglio avere la possibilità di dichiarare le regole di accesso sui singoli endpoint, allora è
// OBBLIGATORIA l'annotazione @EnableMethodSecurity
public class Config {

    // Per poter configurare a piacimento la Security Filter Chain dobbiamo creare un Bean
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        // Possiamo disabilitare dei comportamenti di default
        httpSecurity.formLogin(http -> http.disable()); // Non voglio il form di login (avremo React per quello)
        httpSecurity.csrf(http -> http.disable()); // Non voglio la protezione da CSRF (per l'applicazione media non è necessaria e complicherebbe tutta la faccenda, anche lato FE)
        httpSecurity.sessionManagement(http -> http.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // Non voglio le sessioni (perché utilizzeremo la token based authentication con JWT)

        httpSecurity.cors(Customizer.withDefaults());
        // Possiamo aggiungere dei filtri custom

        // Aggiungere/Rimuovere determinate regole di protezione per gli endpoint
        // Possiamo decidere se debba essere necessaria o meno un'autenticazione per accedere agli endpoint
        httpSecurity.authorizeHttpRequests(http -> http.requestMatchers("/**").permitAll());

        return httpSecurity.build();
    }

    @Bean
    @Primary
    PasswordEncoder getBCrypt(){
        return new BCryptPasswordEncoder(11);
        // 11 è il NUMERO DI ROUNDS, ovvero quante volte viene eseguito l'algoritmo. Questo valore
        // è utile per poter personalizzare la velocità di esecuzione di BCrypt. Più è veloce, meno sicure
        // saranno le password, e ovviamente viceversa. Bisogna comunque tenere sempre in considerazione
        // anche il fatto che se lo rendessimo estremamente lento peggiorerebbe la UX. Bisogna trovare il
        // giusto bilanciamento tra le 2.
        // 11 significa che l'algoritmo ogni volta viene eseguito 2^11 volte cioè 2048 volte. Su un computer
        // di prestazioni medie vuol dire circa 100/200 ms
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        config.setAllowedMethods(Arrays.asList("*"));
        config.setAllowedHeaders(Arrays.asList("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        // Registro la configurazione CORS appena fatta a livello globale su tutti gli endpoint del mio server

        return source;

    }
}