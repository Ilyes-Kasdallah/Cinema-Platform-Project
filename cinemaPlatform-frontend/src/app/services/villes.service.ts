import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable} from "rxjs";
import {Ville} from "../models/ville.model";

@Injectable()
export class VillesService {

    private villesUrl: string;

    constructor(private http: HttpClient) {
        this.villesUrl = 'http://localhost:8080/villes';
    }

    getEmbedded() : Observable<any> {
        return this.http.get<Ville[]>(this.villesUrl);
    }

}
