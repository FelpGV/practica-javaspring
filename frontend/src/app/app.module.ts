import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ProductComponent } from './components/product/product.component';
import { CustomerComponent } from './components/customer/customer.component';
import { HeaderComponent } from './components/header/header.component';
import { ProductCategoryComponent } from './components/product-category/product-category.component';


@NgModule({
  declarations: [
    AppComponent,
    ProductComponent,
    CustomerComponent,
    HeaderComponent,
    ProductCategoryComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    AppRoutingModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
