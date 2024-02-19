import { Injectable } from '@angular/core';
import { Observable } from "rxjs";
import { HttpClient } from "@angular/common/http";
import { Cinema } from "../models/cinema.model";
import { Ville } from "../models/ville.model";

@Injectable({
  providedIn: 'root'
})
export class CinemaService {

  private villesUrl: string;

  public host: string = "http://localhost:8080";

  constructor(private http: HttpClient) {
    this.villesUrl = 'http://localhost:8080/villes/1/cinemas';
  }

  public getVilles() {
    return this.http.get(this.host + "/villes");
  }

  public getCinema(v: any) { // Adjust the type of parameter v
    return this.http.get(v._link.cinemas.href); // Adjust property name to _link
  }

  public getSalles(c: any) {
    return this.http.get(c._links.salles.href);
  }

  getLinks(): Observable<any> {
    return this.http.get<Cinema[]>(this.villesUrl);
  }

  getProjections(salle : any) {
    let url = salle._links.projections.href.replace("{?projection}","");
    return this.http.get(url+"?projection=p1");
  }

  getTicketsPlaces(p: any) {
    let url = p._links.tickets.href.replace("{?projection}","");
    return this.http.get(url+"?projection=ticketsProj");

  }

  getEmbedded(): Observable<any> {
    return this.http.get<Ville[]>(`${this.host}/villes`);
  }

  payerTickets(dataForm: any) {
    return this.http.post(this.host+"/payerTickets", dataForm);

  }
}
