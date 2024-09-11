import { Component } from '@angular/core';
import {HeaderComponent} from "../header/header.component";
import {FormsModule, NgForm} from "@angular/forms";
import {NgIf} from "@angular/common";
import {ApiService} from "../api-service/api-service.service";
import {HttpClient, HttpClientModule} from "@angular/common/http";

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

  constructor(private apiService:ApiService) {
  }
  onSubmit(form:NgForm) {
    if(form.valid){
      const formData=form.value;
      console.log('Form submitted'+form.value.email);
      this.apiService.login(form.value.email,form.value.password).subscribe(value => value.contains("ok"));
    }

  }
}
