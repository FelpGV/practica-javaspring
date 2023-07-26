import { Injectable } from '@angular/core';
import { Product } from '../models/product';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PageResponse } from '../models/pageResponse';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  products: Product[] = [];

  constructor(private http: HttpClient) { }

 public getProducts(page:number): Observable<PageResponse<Product>> {
   return this.http.get<PageResponse<Product>>(`http://localhost:8080/api/products?page=${page}`);
 }
}
