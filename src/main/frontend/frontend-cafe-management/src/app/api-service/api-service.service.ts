
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {Product} from "../../models/product";

@Injectable({
  providedIn:  'root'
})
export class ApiService{

  private url = 'http://localhost:8081/';

  constructor(private http: HttpClient) { }

  getPosts() {
    return this.http.get(this.url);
  }

  login(email: string, password: string): Observable<any> {
    const headers = { 'Content-Type': 'application/json' };

    // Create an object with email and password and convert it to JSON
    const body = JSON.stringify({ email, password });

    // Make the POST request with the correct body and headers
    return this.http.post(this.url + "user/login", body, { headers });
  }

  signUp(email:string,password:string,contact_number:string,name:string):Observable<any>{
    const headers = { 'Content-Type': 'application/json' };

    // Create an object with email and password and convert it to JSON
    const body = JSON.stringify({ email, password,name,contact_number });

    // Make the POST request with the correct body and headers
    return this.http.post(this.url + "user/signup", body, { headers });
  }

  addCategory(name: string): Observable<any> {
    // Retrieve the token from session storage
    const token = sessionStorage.getItem("token");

    // Set up headers including Authorization and Content-Type
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': token ? `Bearer ${token}` : ''
    });

    // Convert the category name to a JSON object
    const body = JSON.stringify({ name });

    // Make the POST request with the body and headers
    return this.http.post<any>(`${this.url}category/add`, body, { headers });
  }
  getAlCategory(): Observable<any> {
    // Retrieve the token from session storage
    const token = sessionStorage.getItem("token");

    // Set up headers including Authorization and Content-Type
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': token ? `Bearer ${token}` : ''
    });

    // Convert the category name to a JSON object
    const body = JSON.stringify({ name });

    // Make the POST request with the body and headers
    return this.http.get<any>(`${this.url}category/getAll`, {headers});
  }



  addProduct(id: string,categoryId:string,name:string,price:string,description:string): Observable<any> {
    // Retrieve the token from session storage
    const token = sessionStorage.getItem("token");

    // Set up headers including Authorization and Content-Type
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': token ? `Bearer ${token}` : ''
    });

    // Convert the category name to a JSON object
    const body = JSON.stringify({ id,categoryId,name,price,description });

    // Make the POST request with the body and headers
    return this.http.get<any>(`${this.url}product/add`, {headers});
  }

  updateProduct(id: string,categoryId:string,name:string,price:string,description:string): Observable<any> {
    // Retrieve the token from session storage
    const token = sessionStorage.getItem("token");

    // Set up headers including Authorization and Content-Type
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': token ? `Bearer ${token}` : ''
    });

    // Convert the category name to a JSON object
    const body = JSON.stringify({ id,categoryId,name,price,description });

    // Make the POST request with the body and headers
    return this.http.get<any>(`${this.url}product/update`, {headers});
  }

  getAllProducts(): Observable<any> {
    // Retrieve the token from session storage
    const token = sessionStorage.getItem("token");

    // Set up headers including Authorization and Content-Type
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': token ? `Bearer ${token}` : ''
    });

    // Convert the category name to a JSON object
    const body = JSON.stringify({ name });

    // Make the POST request with the body and headers
    return this.http.get<any>(`${this.url}product/getAll`, {headers});
  }

  deleteProduct(id: string): Observable<any> {
    // Retrieve the token from session storage
    const token = sessionStorage.getItem("token");

    // Set up headers including Authorization and Content-Type
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': token ? `Bearer ${token}` : ''
    });

    // Make the DELETE request, passing the product ID in the URL
    return this.http.delete<any>(`${this.url}product/delete/${id}`, { headers });
  }
    getProductByID(id: string): Observable<any> {
    // Retrieve the token from session storage
    const token = sessionStorage.getItem("token");

    // Set up headers including Authorization and Content-Type
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': token ? `Bearer ${token}` : ''
    });

    // Make the DELETE request, passing the product ID in the URL
    return this.http.delete<any>(`${this.url}product/getByID/${id}`, { headers });
  }

  getProductByCategory(categoryID: string): Observable<any> {
    // Retrieve the token from session storage
    const token = sessionStorage.getItem("token");

    // Set up headers including Authorization and Content-Type
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': token ? `Bearer ${token}` : ''
    });

    // Make the DELETE request, passing the product ID in the URL
    return this.http.delete<any>(`${this.url}product/getByCategory/${categoryID}`, { headers });
  }

  //
  // private apiUrl = 'http://localhost:8081'; // Your API URL
  //
  //
  // getAllProducts(): Observable<Product[]> {
  //   return this.http.get<Product[]>(this.apiUrl+"product/getAll");
  // }
  //
  // getProductsByCategory(categoryId: number): Observable<Product[]> {
  //   return this.http.get<Product[]>(`${this.apiUrl}category/${categoryId}`);
  // }

}
