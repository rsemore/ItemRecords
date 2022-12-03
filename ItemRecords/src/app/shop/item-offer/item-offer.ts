import {Item} from "../../items/item";

export interface ItemOffer {
  item: Item;
  price: number;
  description: string;
  startDate: string;
  endDate: string;
}
