import {Observable} from "rxjs";
import {customerModel} from "../modules/models/customerModel";
import {customerOrErrorsModel} from "../modules/models/customerOrErrorsModel";

export interface CustomerService {
  //findCustomers(): Observable<customerModel[]>;

  checkAndSaveCustomer(customer: customerModel): Observable<customerOrErrorsModel>;

 // deleteCustomerById(customerId: string): Observable<void>;

  //findCustomerById(customerId: string): Observable<customerModel>;

  //isActiveCustomerById(customerId: string): Observable<boolean>;

  updateCustomerPersonalInf(customer: customerModel): Observable<customerOrErrorsModel>;

  saveCustomerWallet(customer: customerModel): Observable<customerOrErrorsModel>;

  deleteCard(customer: customerModel): Observable<void>;

  replenishCard(customer: customerModel): Observable<customerOrErrorsModel>;
}
