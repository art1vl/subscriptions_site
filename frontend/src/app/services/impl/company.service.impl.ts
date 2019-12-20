import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {CompanyService} from "../company.service";
import {companyModel} from "../../modules/models/companyModel";
import {Observable} from "rxjs";
import {companyOrErrorsModel} from "../../modules/models/companyOrErrorsModel";
import {CompanyPageModel} from "../../modules/models/CompanyPageModel";

@Injectable()
export class CompanyServiceImpl implements CompanyService {
  company: companyModel;

  constructor(private http: HttpClient) {
  }

  findAllCompanies(): Observable<companyModel[]> {
    return this.http.get<companyModel[]>('/api/company');
  }

  findAllByPage(pageNumber: number, amount: number): Observable<CompanyPageModel> {
    return this.http.get<CompanyPageModel>('/api/company/all?page=' + pageNumber + "&amount=" + amount);
  }

  findCompanyById(id: number): Observable<companyModel> {
    return this.http.get<companyModel>('/api/company/' + id);
  }

  saveCompany(company: companyModel): Observable<companyOrErrorsModel> {
    return this.http.post<companyOrErrorsModel>('/api/company', company);
  }

  saveCompanyWallet(company: companyModel): Observable<companyOrErrorsModel> {
    return this.http.post<companyOrErrorsModel>('/api/company/wallet', company);
  }

  findCompanyByLogInInfId(logInInfId: number): Observable<companyModel> {
    return this.http.get<companyModel>('/api/company/log/in/inf/' + logInInfId);
  }

  changeStatus(companyModel: companyModel): Observable<companyOrErrorsModel> {
    return this.http.put<companyOrErrorsModel>('/api/company/status', companyModel);
  }
}
