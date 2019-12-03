import {companyModel} from "./companyModel";

export class companyOrErrorsModel {
  companyModel: companyModel = new companyModel();
  errors: Map<string, string> = new Map<string, string>();
}
