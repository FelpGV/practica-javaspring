import { Component, Input, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Product } from 'src/app/models/product';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent {
  @Input() product!: Product;
  constructor() {
   }
  http = inject(HttpClient);
  title = 'frontend';

  products: Product[] = [];

  ngOnInit() {
     this.http.get<Product[]>('http://localhost:8080/api/products/')
      .subscribe((data: Product[]) => {
        this.products = data;
        console.log(this.products);

      });
      console.log(this.products);
  }
}
