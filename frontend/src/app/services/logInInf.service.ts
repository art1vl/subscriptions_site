import {Observable} from "rxjs";
import {SignInModel} from "../modules/models/SignInModel";

export interface LogInInfService {
  signin (email: string, password: string): Observable<SignInModel>
}
