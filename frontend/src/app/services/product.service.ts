import {Observable} from "rxjs";
import {ProductModel} from "../modules/models/productModel";
import {ProductOrErrorModel} from "../modules/models/productOrErrorModel";
import {ProductPageModel} from "../modules/models/productPageModel";

export interface ProductService {
  findProducts(page: number, amount: number): Observable<ProductPageModel>;

  saveProduct(product: ProductModel): Observable<ProductOrErrorModel>;

  saveProductImageByProductId(productId: number, file: File): Observable<any>;

  deleteProductById(productId: string): Observable<void>;

  findProductById(productId: string): Observable<ProductModel>;
}
