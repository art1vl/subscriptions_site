import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ProductModel} from "../../modules/models/productModel";
import {ProductService} from "../product.service";

@Injectable()
// Data service
export class ProductServiceImpl implements ProductService {

  constructor(private http: HttpClient) {
  }

  // Ajax request for billing account data
  findProducts(): Observable<ProductModel[]> {
    return this.http.get<ProductModel[]>('/api/product');
  }

  findProductTypes(): Observable<String[]> {
    return this.http.get<String[]>('/api/product/types');
  }

  findProductById(productId: string): Observable<ProductModel> {
    return this.http.get<ProductModel>('/api/product/' + productId);
  }

  //todo
  saveProduct(product: ProductModel): Observable<ProductModel> {
    return this.http.post<ProductModel>('/api/product', product);
  }

  //todo
  deleteProductById(productId: string): Observable<void> {
    return this.http.delete<void>('/api/product/' + productId);
  }
}
