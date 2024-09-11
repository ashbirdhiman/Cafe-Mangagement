
import { HttpClient } from  '@angular/common/http';
import {Injectable} from "@angular/core";
import {Observable} from "rxjs";

@Injectable({
  providedIn:  'root'
})
export class ApiService{

  private url = 'localhost:8081/';

  constructor(private http: HttpClient) { }

  getPosts() {
    return this.http.get(this.url);
  }
  login(email:string,password:string):Observable<any>{
    const headers = { 'content-type': 'application/json'}
    const body=JSON.stringify(email+password);
   return this.http.post(this.url+"/login",body,{'headers':headers});
    }

  signUp(email:string,password:string,contact_Number:string,name:string):Observable<any>{
    const headers = { 'content-type': 'application/json'}
    const body=JSON.stringify(email+password+contact_Number+name);
    return this.http.post(this.url+"/login",body,{'headers':headers});
  }


}
