import { Component } from '@angular/core';
import {HeaderComponent} from "../header/header.component";
import {FormsModule, NgForm} from "@angular/forms";
import {NgIf} from "@angular/common";
import {ApiService} from "../api-service/api-service.service";
import {HttpClient, HttpClientModule} from "@angular/common/http";
import { Router } from '@angular/router';


@Component({
  selector: 'app-log-in',
  standalone: true,
  imports: [
    HeaderComponent,
    FormsModule,
    NgIf,
    HttpClientModule
  ],
  templateUrl: './log-in.component.html',
  styleUrl: './log-in.component.scss'
})
export class LogInComponent {

  constructor(private apiService:ApiService,private router:Router) {
  }
  onSubmit(form:NgForm) {
    if(form.valid){
      const formData=form.value;
      console.log('Form submitted'+form.value.email);
      this.apiService.login(form.value.email, form.value.password).subscribe(response => {
        if (response && response.token) {
          // Token exists, proceed with your logic
          console.log("Token found:", response.token);
          sessionStorage.setItem("token",response.token);
          console.log(sessionStorage.getItem("token"));
        } else {
          // Handle the case where there is no token
          console.log("Token not found or invalid response");
          this.router.navigate(['/dashboard']);
        }
      }, error => {
        // Handle any error from the API
        console.error("Login error:", error);
      });
    }


  }
}
