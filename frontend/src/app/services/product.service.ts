import {Observable} from "rxjs";
import {ProductModel} from "../modules/models/productModel";
import {ProductOrErrorModel} from "../modules/models/productOrErrorModel";
import {ProductPageModel} from "../modules/models/productPageModel";

export interface ProductService {
  findProductsIsActive(page: number, amount: number): Observable<ProductPageModel>;

  findAllProducts(page: number, amount: number): Observable<ProductPageModel>;

  saveProduct(product: ProductModel): Observable<ProductOrErrorModel>;

  saveProductImageByProductId(productId: number, file: File): Observable<any>;

  deleteProductById(productId: number): Observable<void>;

  findProductById(productId: number): Observable<ProductModel>;

  findAllProductsByCompanyId(companyId: number, page: number, amount: number): Observable<ProductPageModel>;

  changeProductStatus(product: ProductModel): Observable<ProductOrErrorModel>;

  searchProductsByPage(param: Map<string, string>, pageNumber: number, amount: number): Observable<ProductPageModel>;
}
