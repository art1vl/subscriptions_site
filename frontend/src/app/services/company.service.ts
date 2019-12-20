import {Observable} from "rxjs";
import {companyModel} from "../modules/models/companyModel";
import {companyOrErrorsModel} from "../modules/models/companyOrErrorsModel";
import {CompanyPageModel} from "../modules/models/CompanyPageModel";

export interface CompanyService {
  findAllCompanies(): Observable<companyModel[]>;

  saveCompany(company: companyModel): Observable<companyOrErrorsModel>;

  saveCompanyWallet(company: companyModel): Observable<companyOrErrorsModel>;

  findCompanyByLogInInfId(logInInfId: number): Observable<companyModel>;

  findCompanyById(id: number): Observable<companyModel>;

  findAllByPage(pageNumber: number, amount: number): Observable<CompanyPageModel>;

  changeStatus(companyModel: companyModel): Observable<companyOrErrorsModel>;
}
