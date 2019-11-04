import {Observable} from "rxjs";
import {Customer} from "../modules/models/registration-page/customer";

export interface CustomerService {
  getCustomers(): Observable<Customer[]>;

  checkAndSaveCustomer(costumer: Customer): Observable<boolean>;

  deleteCustomerById(costumerId: string): Observable<void>;

  getCustomerById(costumerId: string): Observable<Customer>;

  isActiveCustomerById(costumerId: string): Observable<boolean>;
}
