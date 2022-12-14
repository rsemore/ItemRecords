import {Category} from "./category";

export interface Item {
  itemName: string
  category: Category
  itemDescription: string
  manufacturer: string
  yearOfManufacture: number
}
