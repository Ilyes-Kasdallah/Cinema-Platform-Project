import {Ticket} from "./ticket.model";

export class Place {
  id?: number;
  numero?: number;
  longitude?: number;
  latitude?: number;
  altitude?: number;
  tickets?: Ticket[];
}
