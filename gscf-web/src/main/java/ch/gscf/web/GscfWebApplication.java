package ch.gscf.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({
	"ch.gscf.service",
	"ch.gscf.service.dto",
	"ch.gscf.web"
})
public class GscfWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(GscfWebApplication.class, args);
	}

}
