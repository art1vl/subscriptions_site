import {Observable} from "rxjs";
import {companyModel} from "../modules/models/companyModel";
import {companyOrErrorsModel} from "../modules/models/companyOrErrorsModel";

export interface CompanyService {
  findAllCompanies(): Observable<companyModel[]>;

  saveCompany(company: companyModel): Observable<companyOrErrorsModel>;

  saveCompanyWallet(company: companyModel): Observable<companyOrErrorsModel>;
}
