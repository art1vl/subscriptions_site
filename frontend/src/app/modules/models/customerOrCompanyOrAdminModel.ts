import {customerModel} from "./customerModel";
import {companyModel} from "./companyModel";
import {adminModel} from "./adminModel";

export class CustomerOrCompanyOrAdminModel {
  customerModel: customerModel;
  companyModel: companyModel;
  adminModel: adminModel;
  errors: Map<string, string>;
}
