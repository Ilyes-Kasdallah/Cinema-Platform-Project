import {Cinema} from "./cinema.model";

export class Ville {
  id?: number;
  name?: string;
  longitude?: number;
  latitude?: number;
  altitude?: number;
  cinemas?: Cinema[];
}
