import {Observable} from "rxjs";
import {customerOrCompanyOrAdminOrErrorsModel} from "../modules/models/customerOrCompanyOrAdminOrErrorsModel";

export interface LogInInfService {
  signin (email: string, password: string): Observable<customerOrCompanyOrAdminOrErrorsModel>
}
