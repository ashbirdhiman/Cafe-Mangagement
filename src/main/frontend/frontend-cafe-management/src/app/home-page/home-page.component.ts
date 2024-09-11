import { Component } from '@angular/core';
import {HeaderComponent} from "../header/header.component";
import {NgForOf} from "@angular/common";
import {ItemsDisplayComponent} from "../items-display/items-display.component";
import {FooterComponent} from "../footer/footer.component";

interface FeaturedSpecial {
  image: string;
  title: string;
  description: string;
  price: number;
}
@Component({
  selector: 'app-home-page',
  standalone: true,
  imports: [
    HeaderComponent,
    NgForOf,
    ItemsDisplayComponent,
    FooterComponent
  ],
  templateUrl: './home-page.component.html',
  styleUrl: './home-page.component.scss'
})
export class HomePageComponent {

  featuredSpecials: FeaturedSpecial[] = [
    {
      image: 'path/to/pizza1.jpg',
      title: 'Pizza 1',
      description: 'Description for Pizza 1',
      price: 15.99,
    },
    {
      image: 'path/to/pizza2.jpg',
      title: 'Pizza 2',
      description: 'Description for Pizza 2',
      price: 12.99,
    }
  ];
}

