package net.guides.springboot2.cinemaApp.web;

import jakarta.transaction.Transactional;
import lombok.Data;
import net.guides.springboot2.cinemaApp.dao.FilmRepository;
import net.guides.springboot2.cinemaApp.dao.TicketRepository;
import net.guides.springboot2.cinemaApp.entities.Film;
import net.guides.springboot2.cinemaApp.entities.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
public class CinemaRestController {
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @GetMapping(path = "/imageFilm/{id}", produces= MediaType.IMAGE_PNG_VALUE)
    public byte[] image(@PathVariable (name = "id")Long id) throws Exception{
        Film f=filmRepository.findById(id).get();
        String photoName=f.getPhoto();
        File file = new File("C:/Users/ilyes/cinemaApp/src/main/resources/images/" + photoName);
        Path path= Paths.get(file.toURI());
        return Files.readAllBytes(path);

    }
    @PostMapping("/payerTickets")
    @Transactional
    public List<Ticket> payerTickets(@RequestBody TicketForm ticketForm) {
        List<Ticket> listTickets=new ArrayList<>();
        ticketForm.getTickets().forEach(idTicket->{
            Ticket ticket=ticketRepository.findById(idTicket).get();
            ticket.setNomClient(ticketForm.getNomClient());
            ticket.setReserve(true);
            ticketRepository.save(ticket);
            listTickets.add(ticket);
        });
        return listTickets;
    }


}
@Data
class TicketForm{
    private String nomClient;
    private List<Long> tickets=new ArrayList<>();

}
