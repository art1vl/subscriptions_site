import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Product} from "../../modules/models/catalog-page/product";
import {CustomerService} from "../customer.service";
import {Customer} from "../../modules/models/registration-page/customer";

@Injectable()
// Data service
export class CustomerServiceImpl implements CustomerService {

  constructor(private http: HttpClient) {
  }

  deleteCustomerById(costumerId: string): Observable<void> {
    return undefined;
  }

  getCustomerById(costumerId: string): Observable<Customer> {
    return undefined;
  }

  getCustomers(): Observable<Customer[]> {
    return undefined;
  }

  isActiveCustomerById(costumerId: string): Observable<boolean> {
    return undefined;
  }

  checkAndSaveCustomer(costumer: Customer): Observable<boolean> {
    return this.http.post<boolean>('/api/customer', costumer);
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
