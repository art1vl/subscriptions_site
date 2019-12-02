import {customerModel} from "./customerModel";
import {companyModel} from "./companyModel";
import {adminModel} from "./adminModel";

export class customerOrCompanyOrAdminOrErrorsModel {
  customerModel: customerModel = new customerModel();
  companyModel: companyModel = new companyModel();
  adminModel: adminModel = new adminModel();
  errors: Map<string, string> = new Map<string, string>();
}
