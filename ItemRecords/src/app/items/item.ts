import {Category} from "./category";

export interface Item {

  itemId: number;
  itemName: string;
  category: Category;
  itemDescription: string;
}
