import {Component, OnInit} from '@angular/core';
import {HeaderComponent} from "../header/header.component";
import {MatSidenavContainer, MatSidenavContent} from "@angular/material/sidenav";
import {MatList, MatListItem} from "@angular/material/list";
import {MatCard, MatCardActions, MatCardContent, MatCardHeader,MatCardTitle,MatCardSubtitle} from "@angular/material/card";
import {MatIcon} from "@angular/material/icon";
import {MatButton, MatIconButton} from "@angular/material/button";
import {NgForOf} from "@angular/common";
import {MatSidenavModule} from '@angular/material/sidenav'
import {ApiService} from "../api-service/api-service.service";
import {Product} from "../../models/product";
import {Category} from "../../models/category";

@Component({
  selector: 'app-user-dashboard',
  standalone: true,
  imports: [
    HeaderComponent,
    MatSidenavContent,
    MatListItem,
    MatSidenavContainer,
    MatList,
    MatCard,
    MatCardHeader,
    MatCardContent,
    MatCardActions,
    MatCardSubtitle,
    MatCardTitle,
    MatIcon,
    MatButton,
    MatIconButton,
    NgForOf,
    MatSidenavModule
  ],
  templateUrl: './user-dashboard.component.html',
  styleUrl: './user-dashboard.component.scss',
})
export class UserDashboardComponent implements OnInit{

  constructor(private apiService:ApiService) {
  }
  ngOnInit(): void {
    this.apiService.getAllProducts().subscribe(data => {
      this.products = data;
    });
    this.apiService.getAlCategory().subscribe(data => {
      this.categories = data;
    });
  }
  categories:Category[] = []; // Array of categories
  products:Product[] = []; // Array of products filtered by selected category
  cart:Product[] = []; // Array to store cart items
  //
  selectCategory(category:Category) {
    // Fetch products based on category
  }
  //
  addToCart(product:Product) {
    this.cart.push(product);
  }
  //
  removeFromCart(product:Product) {
    this.cart = this.cart.filter(item => item !== product);
  }
  //
  getTotal() {
    // return this.cart.reduce((acc, product) => acc + product, 0);
  }
  //
  checkout() {
    // Handle checkout process
  }
}
