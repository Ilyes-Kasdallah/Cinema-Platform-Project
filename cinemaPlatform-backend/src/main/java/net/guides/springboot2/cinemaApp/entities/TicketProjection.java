package net.guides.springboot2.cinemaApp.entities;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "ticketsProj", types = Ticket.class)
public interface TicketProjection {
    public Long getId();
    public String getNomClient();
    public double getPrix();
    public int getCodePayment();
    public boolean getReserve();
    public Place getPlace();
}
