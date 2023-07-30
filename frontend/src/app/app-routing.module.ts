import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProductComponent } from './components/product/product.component';
import { CustomerComponent } from './components/customer/customer.component';
import { ProductCategoryComponent } from './components/product-category/product-category.component';

const routes: Routes = [
  { path: '', component: ProductComponent },
  { path: 'customers', component: CustomerComponent },
  { path: 'products/:category', component: ProductComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
