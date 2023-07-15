import { Injectable } from '@angular/core';
import { Product } from '../models/product';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  products: Product[] = [];

  constructor(private http: HttpClient) { }

 public getProducts(): Observable<Product[]> {
   return this.http.get<Product[]>('http://localhost:8080/api/products/');
 }
}
