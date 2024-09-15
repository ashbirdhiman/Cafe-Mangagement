import { Component } from '@angular/core';
import {HeaderComponent} from "../header/header.component";
import {FormsModule, NgForm} from "@angular/forms";
import {NgIf} from "@angular/common";
import {ApiService} from "../api-service/api-service.service";

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
  constructor(private apiService: ApiService) {
  }

  onSubmit(form: NgForm) {
    if (form.valid) {

      console.log('Form submitted' + form.value.email);
      this.apiService.signUp(form.value.email, form.value.password, form.value.contact_number, form.value.name).subscribe(response => {
        if (response && response.message) {
          console.log("sign up "+response.message);
        }
      });
    }
  }
}
