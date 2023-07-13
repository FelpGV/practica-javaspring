import { Component, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Product } from './models/product';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  http = inject(HttpClient);

  title = 'frontend';
  products: Product[] = [];

  ngOnInit() {
    this.http.get<Product>('http://localhost:8080/api/products/')
      .subscribe(data => {
      console.log(data);
    });
  }

}
