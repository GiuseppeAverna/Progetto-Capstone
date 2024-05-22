//package Averna.Giuseppe.Progetto.Capstone;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
//
//@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
//public class ProgettoCapstoneApplication {
//
//	public static void main(String[] args) {
//		SpringApplication.run(ProgettoCapstoneApplication.class, args);
//	}
//
//}


// versione normale con security
package Averna.Giuseppe.Progetto.Capstone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProgettoCapstoneApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProgettoCapstoneApplication.class, args);
	}
}
