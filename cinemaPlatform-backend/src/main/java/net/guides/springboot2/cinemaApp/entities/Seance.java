package net.guides.springboot2.cinemaApp.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;


@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Seance {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Temporal(TemporalType.TIME)
    private Date heureDebut;
}
