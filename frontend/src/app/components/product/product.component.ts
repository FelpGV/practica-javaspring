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


  constructor(private productService: ProductService) { }

  @Input() products: Product[] = [];
  // @Input() page!: PageResponse<Product>;
  isLastPage: boolean = false;
  page: number = 0;
  totalPages:number = 0;
  hoveredIndex = -1;


  onMouseOver(index: number) {
    this.hoveredIndex = index;
  }

  onMouseOut() {
    this.hoveredIndex = -1;
  }



  ngOnInit() {
    this.loadProducts();

  }

  loadProducts() {
    this.productService.getProducts(this.page).subscribe((pageResponse: PageResponse<Product>) => {
      this.products = pageResponse.content;
      this.isLastPage = pageResponse.last;
      if(this.totalPages === 0){
        this.totalPages = pageResponse.totalPages;
      }
    });
  }

  goToPage(page: number) {
    this.page = page;
    this.loadProducts();
  }

  nextPage() {
    this.page++;
    this.loadProducts();
  }

  previousPage() {
    if (this.page > 0) {
      this.page--;
      this.loadProducts();
    }
  }






}
