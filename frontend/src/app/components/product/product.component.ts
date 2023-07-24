import { Component, Input, inject } from '@angular/core';
import { PageResponse } from 'src/app/models/pageResponse';
import { Product } from 'src/app/models/product';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent {

  @Input() products: Product[] = [];
  hovering = -1;


  constructor(private productService: ProductService) { }

  hoveredIndex = -1;

  onMouseOver(index: number) {
    this.hoveredIndex = index;
  }

  onMouseOut() {
    this.hoveredIndex = -1;
  }

  ngOnInit() {
    this.productService.getProducts().subscribe((pageResponse: PageResponse<Product[]>) => {
      this.products = pageResponse.content;
    });
 }




}
