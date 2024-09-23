import { Component } from '@angular/core';
import {Router, RouterLink} from "@angular/router";

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [
    RouterLink
  ],
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss'
})
export class HeaderComponent {
  constructor(private router: Router) {}

  isLoggedIn(): boolean {
    return !sessionStorage.getItem('token'); // Check if the token exists
  }

  logout(): void {
    sessionStorage.removeItem('token'); // Remove token from session storage
    this.router.navigate(['/login']); // Redirect to login page
  }
}
