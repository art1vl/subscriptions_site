import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Product} from "../modules/models/product-page/product";

@Injectable()
// Data service
export class ProductService { //todo create interface

  constructor(private http: HttpClient) {
  }

  // Ajax request for billing account data
  getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>('/api/product');
  }

  saveProduct(product: Product): Observable<Product> {
    return this.http.post<Product>('/api/product', product);
  }

  deleteProductById(productId: number): Observable<void> {
    return this.http.delete<void>('/api/product/' + productId);
  }

  getproductById(productId: string): Observable<Product> {
    return this.http.get<Product>('/api/product/' + productId);
  }

}
