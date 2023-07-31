import { Component, Input, inject } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PageResponse } from 'src/app/models/pageResponse';
import { Product } from 'src/app/models/product';
import { ProductService } from 'src/app/services/product/product.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent {


  constructor(
    private productService: ProductService,
    private route: ActivatedRoute,
    private router:Router
    ) { }

  products: Product[] = [];
  isLastPage: boolean = false;
  page!: number;
  totalPages:number = 0;
  currentPage:number = 0;
  displayedPages: number = 5;
  hoveredIndex = -1;
  category:string = "";


  onMouseOver(index: number) {
    this.hoveredIndex = index;
  }

  onMouseOut() {
    this.hoveredIndex = -1;
  }



  ngOnInit() {
    this.route.params.subscribe(params => {
      this.category = params['category'];
    });
    this.route.queryParams.subscribe(params => {
      this.page = params['page'] ? params['page'] - 1 : 0;
      console.log(this.page);
    });
    if(this.category){
      this.loadProductsByCategory(this.category);
      this.urlPage(this.page);
    }else{
      this.loadProducts();
    }

  }


  loadParams(pageResponse: PageResponse<Product>){
    this.products = pageResponse.content;
      this.isLastPage = pageResponse.last;
      this.currentPage = pageResponse.number;
      if(this.totalPages === 0){
        this.totalPages = pageResponse.totalPages;
      }
  }

  urlPage(page:number){
    this.router.navigate([], {
      queryParams: {
        'page': page+1,
      },
    });
  }

  loadProductsByCategory(category: string) {
    this.productService.getProductByCategory(this.page, category).subscribe((pageResponse: PageResponse<Product>) => {
      this.loadParams(pageResponse);
    });
  }

  loadProducts() {
    this.productService.getProducts(this.page).subscribe((pageResponse: PageResponse<Product>) => {
      this.loadParams(pageResponse);
    });
  }

  goToPage(page: number) {
    this.page = page;
    if(this.category > ""){
      this.loadProductsByCategory(this.category);
    }else{
      this.loadProducts();
    }
    this.urlPage(this.page);
  }

  get startPage(): number {
    let start = this.currentPage - Math.floor(this.displayedPages / 2);
    return Math.max(Math.min(start, this.totalPages - this.displayedPages), 0);
  }
  get endPage(): number {
    return Math.min(this.startPage + this.displayedPages, this.totalPages);
  }


}
