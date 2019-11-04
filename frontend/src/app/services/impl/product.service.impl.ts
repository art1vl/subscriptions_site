import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Product} from "../../modules/models/catalog-page/product";
import {ProductService} from "../product.service";

@Injectable()
// Data service
export class ProductServiceImpl implements ProductService {

  constructor(private http: HttpClient) {
  }

  // Ajax request for billing account data
  getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>('/api/product');
  }

  saveProduct(product: Product): Observable<Product> {
    return this.http.post<Product>('/api/product', product);
  }

  deleteProductById(productId: string): Observable<void> {
    return this.http.delete<void>('/api/product/' + productId);
  }

  getProductById(productId: string): Observable<Product> {
    return this.http.get<Product>('/api/product/' + productId);
  }
}
