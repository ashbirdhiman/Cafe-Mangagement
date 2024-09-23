import {Category} from "./category";

export class Product {
  id: number=0;
  name: string="";
  category: Category=new Category();
  description: string="";
  price: number=0;
  status: string="";
}


