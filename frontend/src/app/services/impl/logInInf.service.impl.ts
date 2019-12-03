import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {LogInInfService} from "../logInInf.service";
import {Observable} from "rxjs";
import {logInParam} from "../../modules/models/logInParam";
import {customerOrCompanyOrAdminOrErrorsModel} from "../../modules/models/customerOrCompanyOrAdminOrErrorsModel";

@Injectable()
export class LogInInfServiceImpl implements LogInInfService {

  constructor(private http: HttpClient) { }

  signin (email: string, password: string): Observable<customerOrCompanyOrAdminOrErrorsModel> {
    let signinParam: logInParam = new logInParam(email, password);
    return this.http.post<customerOrCompanyOrAdminOrErrorsModel>('/api/logInInf/sign/in', signinParam);
  }
}
