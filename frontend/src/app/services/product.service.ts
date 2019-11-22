import {Observable} from "rxjs";
import {Product} from "../modules/models/catalog-page/product";

export interface ProductService {
  getProducts(): Observable<Product[]>;

  saveProduct(product: Product): Observable<Product>;

  deleteProductById(productId: string): Observable<void>;

  getProductById(productId: string): Observable<Product>;
}
