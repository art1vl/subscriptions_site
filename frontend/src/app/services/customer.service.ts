import {Observable} from "rxjs";
import {customerModel} from "../modules/models/customerModel";
import {customerOrErrorsModel} from "../modules/models/customerOrErrorsModel";
import {CustomerPageModel} from "../modules/models/CustomerPageModel";

export interface CustomerService {
  checkAndSaveCustomer(customer: customerModel): Observable<customerOrErrorsModel>;

  findCustomerByLogInInfId(logInInfId: number): Observable<customerModel>;

  updateCustomerPersonalInf(customer: customerModel): Observable<customerOrErrorsModel>;

  saveCustomerWallet(customer: customerModel): Observable<customerOrErrorsModel>;

  findCustomerById(id: number): Observable<customerModel>;

  findAllByPage(pageNumber: number, amount: number): Observable<CustomerPageModel>;

  changeStatus(customerModel: customerModel): Observable<customerOrErrorsModel>;

  liquidateDebt(customerModel: customerModel): Observable<customerOrErrorsModel>;
}
