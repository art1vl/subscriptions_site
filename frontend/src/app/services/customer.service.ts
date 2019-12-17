import {Observable} from "rxjs";
import {customerModel} from "../modules/models/customerModel";
import {customerOrErrorsModel} from "../modules/models/customerOrErrorsModel";

export interface CustomerService {
  findCustomers(): Observable<customerModel[]>;

  checkAndSaveCustomer(customer: customerModel): Observable<customerOrErrorsModel>;

 // deleteCustomerById(customerId: string): Observable<void>;

  findCustomerByLogInInfId(logInInfId: number): Observable<customerModel>;

  //isActiveCustomerById(customerId: string): Observable<boolean>;

  updateCustomerPersonalInf(customer: customerModel): Observable<customerOrErrorsModel>;

  saveCustomerWallet(customer: customerModel): Observable<customerOrErrorsModel>;

  findCustomerById(id: number): Observable<customerModel>;
}
