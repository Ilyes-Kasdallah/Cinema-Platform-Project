import { Component, OnInit } from '@angular/core';
import {Ville} from "../../models/ville.model";
import {CinemaService} from "../../services/cinema.service";
import {Cinema} from "../../models/cinema.model";
import {Projection} from "../../models/projection.model";



// @ts-ignore
 //@ts-ignore
@Component({
  selector: 'app-cinema',
  templateUrl: './cinema.component.html',
  styleUrl: './cinema.component.css'
})
export class CinemaComponent implements OnInit {


  public villes: Ville[] = []; // Explicitly typed array of Ville
  public cinemas: Cinema[] = [];

  public currentVille: Ville | undefined;
  public currentCinema: Cinema | undefined;
  public currentProjection: any;
  protected salles: any;
  public selectedTickets: any;

  constructor(public cinemaService: CinemaService) {
  }
  ngOnInit() {

    this.cinemaService.getEmbedded().subscribe(data => {
      this.villes = data._embedded.villes;
    });

  }

  onGetCinemas(v: Ville) {
    this.currentVille = v;
    this.salles= undefined;
    this.cinemaService.getLinks().subscribe(data => {
      this.cinemas = data._embedded.cinemas; // Assign cinemas directly from _embedded.cinemas
    });
  }

  onGetSalles(c: Cinema) {
     this.currentCinema = c;
     // @ts-ignore
    this.cinemaService.getSalles(c)
       .subscribe(data => {
             this.salles=data;
             this.salles._embedded.salles.forEach((salle: { projections: Object; })=>{
               this.cinemaService.getProjections(salle)
                 .subscribe(data =>{
                   salle.projections=data;
                 },err=>{
                   console.log(err);
                 })
             })

       }, err=> {
         console.log(err);
       })
  }

  onGetTicketsPlaces(p: Projection) {
    this.currentProjection= p;
    // @ts-ignore
    this.cinemaService.getTicketsPlaces(p).subscribe(data =>{
      this.currentProjection.tickets=data;
      this.selectedTickets=[];
    }
    )

  }

  protected readonly CinemaService = CinemaService;

  onSelectTicket(t: any) {
    if(!t.selected){
      t.selected=true;
      this.selectedTickets.push(t);
    }
    else {
      t.selected=false;
      this.selectedTickets.splice(this.selectedTickets.indexOf(t),1);
    }

  }

  getTicketClass(t: any) {
    let str= "btn ticket ";
    if(t.reserve==true){
      str+="btn-danger";
    }
    else if (t.selected){
      str+="btn-warning";
    }
    else {
      str+="btn-success";
    }
    return str;
  }

  // @ts-ignore
  onPayTickets(dataForm) {
    let tickets: any[] =[];
    // @ts-ignore
    this.selectedTickets.forEach(t=>{
      tickets.push(t.id);
    });
    dataForm.tickets=tickets;
    // @ts-ignore
    this.cinemaService.payerTickets(dataForm).subscribe(data=>{
      alert("Tickets réservés avec succès!");
      this.onGetTicketsPlaces(this.currentProjection);
    })

  }
}




