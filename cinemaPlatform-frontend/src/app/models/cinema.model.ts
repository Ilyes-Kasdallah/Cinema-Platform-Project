import {Ville} from "./ville.model";
import {Salle} from "./salle.model";

export class Cinema {
  id?: number;
  name?: string;
  longitude?: number;
  latitude?: number;
  altitude?: number;
  nombreSalles?: number;
  salles?: Salle[];
  ville?: Ville;
}
