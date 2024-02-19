package net.guides.springboot2.cinemaApp;

import net.guides.springboot2.cinemaApp.Services.ICinemaIntService;
import net.guides.springboot2.cinemaApp.entities.Film;
import net.guides.springboot2.cinemaApp.entities.Salle;
import net.guides.springboot2.cinemaApp.entities.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class CinemaAppApplication implements CommandLineRunner {
	@Autowired
	private ICinemaIntService cinemaIntService;

	@Autowired
	private RepositoryRestConfiguration restConfiguration;

	public static void main(String[] args) {
		SpringApplication.run(CinemaAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		restConfiguration.exposeIdsFor(Film.class, Salle.class, Ticket.class);
		cinemaIntService.initVilles();
		cinemaIntService.initCinemas();
		cinemaIntService.initSalles();
		cinemaIntService.initPlaces();
		cinemaIntService.initSeances();
		cinemaIntService.initCategories();
		cinemaIntService.initFilms();
		cinemaIntService.initProjections();
		cinemaIntService.initTickets();

	}
}