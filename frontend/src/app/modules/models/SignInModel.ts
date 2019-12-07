import {UserModel} from "./UserModel";

export class SignInModel {
  error: string;
  user: UserModel;
  token: string;
}
