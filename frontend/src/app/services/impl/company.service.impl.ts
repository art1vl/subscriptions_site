import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {CompanyService} from "../company.service";
import {companyModel} from "../../modules/models/companyModel";
import {Observable} from "rxjs";
import {companyOrErrorsModel} from "../../modules/models/companyOrErrorsModel";

@Injectable()
export class CompanyServiceImpl implements CompanyService {
  company: companyModel;

  constructor(private http: HttpClient) {
  }

  findAllCompanies(): Observable<companyModel[]> {
    return this.http.get<companyModel[]>('/api/company');
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
}
