import { Component } from '@angular/core';
import {NgForOf} from "@angular/common";

@Component({
  selector: 'app-items-display',
  standalone: true,
  imports: [
    NgForOf
  ],
  templateUrl: './items-display.component.html',
  styleUrl: './items-display.component.scss'
})
export class ItemsDisplayComponent {
  pizzas = [
    {
      title: 'Create Your Own',
      description: 'Customize your pizza with your favorite toppings.',
      imageUrl: 'assets/images/create-your-own.jpg'
    },
    {
      title: 'Meat Favourites',
      description: 'Our most popular meat pizzas.',
      imageUrl: 'assets/images/meat-favourites.jpg'
    },
    {
      title: 'Veggie Favourites',
      description: 'Delicious vegetarian pizzas.',
      imageUrl: 'assets/images/veggie-favourites.jpg'
    },
    {
      title: 'Pizza Pizza x Tabasco',
      description: 'A spicy twist on our classic pizza.',
      imageUrl: 'assets/images/pizza-pizza-x-tabasco.jpg'
    },
    {
      title: 'Pizza Pizza x Tabasco',
      description: 'A spicy twist on our classic pizza.',
      imageUrl: 'assets/images/pizza-pizza-x-tabasco.jpg'
    },
    {
      title: 'Pizza Pizza x Tabasco',
      description: 'A spicy twist on our classic pizza.',
      imageUrl: 'assets/images/pizza-pizza-x-tabasco.jpg'
    }
  ];


  // Selected pizza option
  selectedPizza: any = null;

  // Select a pizza option
  selectPizza(pizza: any) {
    this.selectedPizza = pizza;
  }
}
