import {Category} from "./category";

export class Item {

  itemId: number;
  itemName: string;
  category: Category;
  description: string;

  constructor(itemId: number, itemName: string, category: Category, description: string) {
    this.itemId = itemId;
    this.itemName = itemName;
    this.category = category;
    this.description = description;
  }
}
