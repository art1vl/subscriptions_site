import {Injectable} from "@angular/core";
import {HttpClient, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs";
import {ProductModel} from "../../modules/models/productModel";
import {ProductService} from "../product.service";
import {ProductOrErrorModel} from "../../modules/models/productOrErrorModel";
import {ProductPageModel} from "../../modules/models/productPageModel";

@Injectable()
export class ProductServiceImpl implements ProductService {

  constructor(private http: HttpClient) {
  }

  findProductsIsActive(page: number, amount: number): Observable<ProductPageModel> {
    return this.http.get<ProductPageModel>('/api/product?page=' + page + '&amount=' + amount);
  }

  findAllProducts(page: number, amount: number): Observable<ProductPageModel> {
    return this.http.get<ProductPageModel>('/api/product/all?page=' + page + '&amount=' + amount);
  }

  findProductById(productId: string): Observable<ProductModel> {
    return this.http.get<ProductModel>('/api/product/' + productId);
  }

  saveProduct(product: ProductModel): Observable<ProductOrErrorModel> {
    return this.http.post<ProductOrErrorModel>('/api/product', product);
  }

  saveProductImageByProductId(productId: number, file: File): Observable<any> {
    const formData: FormData = new FormData();
    formData.append('file', file);
    const req = new HttpRequest('POST', '/api/product/{id}/image'.replace('{id}', productId.toString()), formData, {
      reportProgress: true,
      responseType: 'json'
    });
    return  this.http.request(req);
  }

  findAllProductsByCompanyId(companyId: number, page: number, amount: number): Observable<ProductPageModel> {
    return this.http.get<ProductPageModel>('/api/product/company/' + companyId + '?page=' + page + '&amount=' + amount);
  }

  changeProductStatus(product: ProductModel): Observable<ProductOrErrorModel> {
    return this.http.put<ProductOrErrorModel>('/api/product', product);
  }

  deleteProductById(productId: number): Observable<void> {
    return this.http.delete<void>('/api/product/' + productId);
  }
}
