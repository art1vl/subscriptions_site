import {Injectable} from "@angular/core";
import {HttpClient, HttpRequest, HttpResponse} from "@angular/common/http";
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

  findProductById(productId: number): Observable<ProductModel> {
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
      responseType: 'json',
    });
    return this.http.request(req);
  }

  findAllProductsByCompanyId(companyId: number, page: number, amount: number): Observable<ProductPageModel> {
    return this.http.get<ProductPageModel>('/api/product/company/' + companyId + '?page=' + page + '&amount=' + amount);
  }

  searchProductsByPage(param: Map<string, string>, pageNumber: number, amount: number): Observable<ProductPageModel> {
    if (param.size == 0) {
      return this.findProductsIsActive(pageNumber, amount);
    } else {
      let paramUrl: string = "";
      let page: string;
      page = "page=" + pageNumber + "&amount=" + amount;
      if (param.get("product") != undefined) {
        paramUrl += "product=" + param.get("product");
      }
      if (param.get("company") != undefined) {
        if (paramUrl.length != 0) {
          paramUrl += "&";
        }
        paramUrl += "company=" + param.get("company");
      }
      if (param.get("min") != undefined) {
        if (paramUrl.length != 0) {
          paramUrl += "&";
        }
        paramUrl += "min=" + param.get("min");
      }
      if (param.get("max") != undefined) {
        if (paramUrl.length != 0) {
          paramUrl += "&";
        }
        paramUrl += "max=" + param.get("max");
      }
      if (param.get("type") != undefined) {
        if (paramUrl.length != 0) {
          paramUrl += "&";
        }
        paramUrl += "type=" + param.get("type");
      }
      paramUrl += "&" + page;
      return this.http.get<ProductPageModel>('/api/product/search?' + paramUrl);
    }
  }

  changeProductStatus(product: ProductModel): Observable<ProductOrErrorModel> {
    return this.http.put<ProductOrErrorModel>('/api/product', product);
  }

  deleteProductById(productId: number): Observable<void> {
    return this.http.delete<void>('/api/product/' + productId);
  }
}
