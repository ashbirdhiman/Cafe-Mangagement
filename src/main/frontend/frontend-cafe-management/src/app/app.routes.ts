import { Routes } from '@angular/router';
import {SignUpComponent} from "./sign-up/sign-up.component";
import {LogInComponent} from "./log-in/log-in.component";
import {UserDashboardComponent} from "./user-dashboard/user-dashboard.component";
import {HomePageComponent} from "./home-page/home-page.component";

export const routes: Routes = [
    {path:'signup',component:SignUpComponent},
    {path:'login',component:LogInComponent},
    {path:'dashboard',component:UserDashboardComponent},
    {path:'home',component:HomePageComponent},
    { path: '', redirectTo: 'home', pathMatch: 'full' },
];
