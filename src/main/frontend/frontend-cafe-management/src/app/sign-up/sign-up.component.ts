import { Component } from '@angular/core';
import {HeaderComponent} from "../header/header.component";
import {FormsModule, NgForm} from "@angular/forms";
import {NgIf} from "@angular/common";

@Component({
  selector: 'app-sign-up',
  standalone: true,
  imports: [
    HeaderComponent,
    FormsModule,
    NgIf
  ],
  templateUrl: './sign-up.component.html',
  styleUrl: './sign-up.component.scss'
})
export class SignUpComponent {
  onSubmit(loginForm:NgForm) {

    // Here you can handle form submission
    // For example, you might want to use Angular's HttpClient to send a request to your backend
    console.log('Form submitted');

  }

  // protected readonly name = name;
}
