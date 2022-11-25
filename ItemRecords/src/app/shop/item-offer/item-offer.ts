import {Item} from "../../items/item";

export interface ItemOffer {
  offerId: number;
  item: Item;
  price: number;
  description: string;
  startDate: string;
  endDate: string;
}
