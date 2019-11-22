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
    console.log("new");
  }

  findAllCompanies(): Observable<companyModel[]> {
    return this.http.get<companyModel[]>('/api/company');
  }

  saveCompany(company: companyModel): Observable<companyOrErrorsModel> {
    return this.http.post<companyOrErrorsModel>('/api/company', company);
  }
}
