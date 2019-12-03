import {Observable} from "rxjs";
import {ProductModel} from "../modules/models/productModel";
import {ProductOrErrorModel} from "../modules/models/productOrErrorModel";

export interface ProductService {
  findProducts(): Observable<ProductModel[]>;

  saveProduct(product: ProductModel): Observable<ProductOrErrorModel>;

  saveProductImageByProductId(productId: number, file: File): Observable<any>;

  deleteProductById(productId: string): Observable<void>;

  findProductById(productId: string): Observable<ProductModel>;
}
