import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {LogInInfService} from "../logInInf.service";
import {Observable, of} from "rxjs";
import {logInParam} from "../../modules/models/logInParam";
import {SignInModel} from "../../modules/models/SignInModel";
import {catchError} from "rxjs/operators";
import {parseHttpResponse} from "selenium-webdriver/http";

@Injectable()
export class LogInInfServiceImpl implements LogInInfService {

  constructor(private http: HttpClient) {
  }

  signin(email: string, password: string): Observable<SignInModel> {
    let signinParam: logInParam = new logInParam(email, password);
    localStorage.clear();
    return this.http.post<SignInModel>('/api/logInInf/sign/in', signinParam);
    //  .pipe(.map(signInModel => {
    //      return signInModel;
    //     }),
    //   catchError(err => {
    //     localStorage.clear();
    //   })
    // ))
    // .subscribe(x => console.log(x));
    // .pipe(
    //   catchError(() => {
    //       console.log("error");
    //       localStorage.clear();
    //       this.http.post<SignInModel>('/api/logInInf/sign/in', signinParam);
    //       return of(null)
    //     }
    //   ));
  }

  // handleError(error: HttpErrorResponse) {
  //   console.log("lalalalalalalala");
  //   return throwError(error);
  // }
}
