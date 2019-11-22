import {CustomerService} from "../customer.service";
import {customerModel} from "../../modules/models/customerModel";
import {HttpClient} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {catchError} from "rxjs/operators";
import {customerOrErrorsModel} from "../../modules/models/customerOrErrorsModel";
import {logInParam} from "../../modules/models/logInParam";
import {Injectable} from "@angular/core";

@Injectable()
export class CustomerServiceImpl implements CustomerService {
  customer: customerModel;

  constructor(private http: HttpClient) {
    console.log("new");
  }

  // public getCustomer() {
  //   if (this.customer == null) {
  //     return null;
  //   }
  //   else {
  //     return customerModel.cloneBase(this.customer);
  //   }
  // }

  // public setCustomer(value: customerModel) {
  //   this.customer = value;
  // }

  deleteCustomerById(customerId: string): Observable<void> {
    return undefined;
  }

  findCustomerById(customerId: string): Observable<customerModel> {
    return this.http.get<customerModel>('/api/customer/' + customerId)
      .pipe(
        catchError(value => {
          // this.errorHandligService(value)
          return of(null);
        })
      );
  }

  findCustomers(): Observable<customerModel[]> {
    return undefined;
  }

  isActiveCustomerById(customerId: string): Observable<boolean> {
    return undefined;
  }

  checkAndSaveCustomer(customer: customerModel): Observable<customerOrErrorsModel> {
    return this.http.post<customerOrErrorsModel>('/api/customer', customer)
      .pipe(
        catchError(value => {
          // this.errorHandligService(value)
          return of(null);
        })
      );
  }

  signin (email: string, password: string): Observable<customerOrErrorsModel> {
    let signinParam: logInParam = new logInParam(email, password);
    return this.http.post<customerOrErrorsModel>('/api/customer/sign/in', signinParam);
  }

  saveCustomerWallet(customer: customerModel): Observable<customerOrErrorsModel> {
    return this.http.post<customerOrErrorsModel>('/api/customer/walletByIdWallet', customer);
  }

  deleteCard(customer: customerModel): Observable<void> {
    return this.http.put<void>('/api/customer/delete/walletByIdWallet', customer);
  }

  replenishCard(customer: customerModel): Observable<customerOrErrorsModel> {
    return this.http.put<customerOrErrorsModel>('/api/customer/replenish/walletByIdWallet', customer);
  }

  updateCustomerPersonalInf(customer: customerModel): Observable<customerOrErrorsModel> {
    return this.http.put<customerOrErrorsModel>('/api/customer', customer)
  }
}
