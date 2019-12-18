import {CustomerService} from "../customer.service";
import {customerModel} from "../../modules/models/customerModel";
import {HttpClient} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {catchError} from "rxjs/operators";
import {customerOrErrorsModel} from "../../modules/models/customerOrErrorsModel";
import {Injectable} from "@angular/core";
import {CustomerPageModel} from "../../modules/models/CustomerPageModel";

@Injectable()
export class CustomerServiceImpl implements CustomerService {
  customer: customerModel;

  constructor(private http: HttpClient) {
  }

  findCustomerByLogInInfId(logInInfId: number): Observable<customerModel> {
    return this.http.get<customerModel>('/api/customer/log/in/inf/' + logInInfId);
  }


  findCustomerById(id: number): Observable<customerModel> {
    return this.http.get<customerModel>('/api/customer/' + id)
  }

  findAllByPage(pageNumber: number, amount: number): Observable<CustomerPageModel> {
    return this.http.get<CustomerPageModel>('/api/customer/all?page=' + pageNumber + '&amount=' + amount);
  }

  checkAndSaveCustomer(customer: customerModel): Observable<customerOrErrorsModel> {
    return this.http.post<customerOrErrorsModel>('/api/customer', customer);
  }

  saveCustomerWallet(customer: customerModel): Observable<customerOrErrorsModel> {
    return this.http.post<customerOrErrorsModel>('/api/customer/wallet', customer);
  }

  updateCustomerPersonalInf(customer: customerModel): Observable<customerOrErrorsModel> {
    return this.http.put<customerOrErrorsModel>('/api/customer', customer)
  }

  changeStatus(customerModel: customerModel): Observable<customerOrErrorsModel> {
    return this.http.put<customerOrErrorsModel>('/api/customer/status', customerModel);
  }
}
