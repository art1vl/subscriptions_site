import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {LogInInfService} from "../logInInf.service";
import {Observable} from "rxjs";
import {logInParam} from "../../modules/models/logInParam";
import {SignInModel} from "../../modules/models/SignInModel";

@Injectable()
export class LogInInfServiceImpl implements LogInInfService {

  constructor(private http: HttpClient) {
  }

  signin(email: string, password: string): Observable<SignInModel> {
    let signinParam: logInParam = new logInParam(email, password);
    localStorage.clear();
    return this.http.post<SignInModel>('/api/logInInf/sign/in', signinParam);

  }
}
