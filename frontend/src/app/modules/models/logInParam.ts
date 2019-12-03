export class logInParam {
  email: string;
  password: string;

  public constructor(email: string, password: string) {
    this.email = email;
    this.password = password;
  }

  static cloneBase(signinParam: logInParam): logInParam {
    const clonedSigninParam: logInParam = new logInParam(signinParam.email, signinParam.password);
    return clonedSigninParam;
  }
}
