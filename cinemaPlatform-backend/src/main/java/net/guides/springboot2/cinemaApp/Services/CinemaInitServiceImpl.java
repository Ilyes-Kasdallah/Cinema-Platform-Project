package net.guides.springboot2.cinemaApp.Services;

import jakarta.transaction.Transactional;
import net.guides.springboot2.cinemaApp.dao.*;
import net.guides.springboot2.cinemaApp.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@Transactional
@Service
public class CinemaInitServiceImpl implements ICinemaIntService{

    @Autowired
    private VilleRepository villeRepository;
    @Autowired
    private CinemaRepository cinemaRepository;
    @Autowired
    private SalleRepository salleRepository;
    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private SeanceRepository seanceRepository;
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private ProjectionRepository projectionRepository;
    @Autowired
    private CategorieRepository categorieRepository;

    @Override
    public void initVilles() {
        Stream.of("Bizerte", "Tunis", "Sousse", "Ariana").forEach(nameVille->{
            Ville ville= new Ville();
            ville.setName(nameVille);
            villeRepository.save(ville);
        });
    }
    @Override
    public void initCinemas() {
        villeRepository.findAll().forEach(v-> {
            Stream.of("Pathé", "Cinéma Africa", "L'Agora", "Le Parnasse", "Cinévog").forEach(nameCinema -> {
                Cinema cinema=new Cinema();
                cinema.setName(nameCinema);
                cinema.setNombreSalles(3+(int)(Math.random()*7));
                cinema.setVille(v);
                cinemaRepository.save(cinema);
            });
        });
    }

    @Override
    public void initSalles() {
        cinemaRepository.findAll().forEach(cinema -> {
            for (int i=0; i<cinema.getNombreSalles();i++){
                Salle salle=new Salle();
                salle.setName("Salle" +(i+1));
                salle.setCinema(cinema);
                salle.setNombrePlace(15+(int)(Math.random()*20));
                salleRepository.save(salle);
            }
        });

    }

    @Override
    public void initPlaces() {
        salleRepository.findAll().forEach(salle -> {
            for (int i=0; i<salle.getNombrePlace(); i++){
                Place place=new Place();
                place.setNumero(i+1);
                place.setSalle(salle);
                placeRepository.save(place);
            }
        });
    }

    @Override
    public void initSeances() {
        DateFormat dateFormat=new SimpleDateFormat("HH:mm");
        Stream.of("12:00", "15:00", "17:00", "19:00", "21:00").forEach(s -> {
            Seance seance=new Seance();
            try{
                seance.setHeureDebut(dateFormat.parse(s));
                seanceRepository.save(seance);
            } catch (ParseException e) {
                e.printStackTrace();
            }
                }
            );
    }
    @Override
    public void initCategories() {
        Stream.of("Histoire", "Action", "Fiction", "Drama").forEach(cat->{
            Categorie categorie=new Categorie();
            categorie.setName(cat);
            categorieRepository.save(categorie);
        });
    }
    @Override
    public void initFilms() {
        double[] durees =new double[] { 1.5, 2, 2.5,3};
        List<Categorie> categories=categorieRepository.findAll();
        Stream.of("The Godfather","The Shawshank Redemption","Moonfall", "Interstellar","Hacksaw Ridge","The Old Guard","No Exit","The Fault in Our Stars","Triangle","Mr. Bean").forEach(f -> {
            Film film = new Film();
            film.setTitre(f);
            film.setDuree(durees[new Random().nextInt(durees.length)]);
            film.setPhoto(f.replaceAll(" ","")+".png");
            film.setCategorie(categories.get(new Random().nextInt(categories.size())));
            filmRepository.save(film);
        });
    }

    @Override
    public void initProjections() {
        double [] prices=new double[] {10, 20, 30, 15, 25};
        List<Film> films=filmRepository.findAll();
        villeRepository.findAll().forEach(ville->{
            ville.getCinemas().forEach(cinema -> {
                cinema.getSalles().forEach(salle -> {
                    int index=new Random().nextInt(films.size());
                    Film film=films.get(index);
                        seanceRepository.findAll().forEach(seance -> {
                            Projection projection=new Projection();
                            projection.setDateProjection(new Date());
                            projection.setFilm(film);
                            projection.setPrix(prices[new Random().nextInt(prices.length)]);
                            projection.setSalle(salle);
                            projection.setSeance(seance);
                            projectionRepository.save(projection);
                        });
                    });
                });
            });

    }

    @Override
    public void initTickets() {
        projectionRepository.findAll().forEach(projection -> {
            projection.getSalle().getPlaces().forEach(place -> {
                Ticket ticket=new Ticket();
                ticket.setPlace(place);
                ticket.setPrix(projection.getPrix());
                ticket.setProjection(projection);
                ticket.setReserve(false);
                ticketRepository.save(ticket);
            });
        });

    }
}
