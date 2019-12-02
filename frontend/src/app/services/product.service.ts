import {Observable} from "rxjs";
import {ProductModel} from "../modules/models/productModel";

export interface ProductService {
  findProducts(): Observable<ProductModel[]>;

  saveProduct(product: ProductModel): Observable<ProductModel>;

  deleteProductById(productId: string): Observable<void>;

  findProductById(productId: string): Observable<ProductModel>;

  findProductTypes(): Observable<String[]>;
}
