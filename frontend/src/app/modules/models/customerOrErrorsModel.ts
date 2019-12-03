import {customerModel} from "./customerModel";

export class customerOrErrorsModel {
  customerModel: customerModel = new customerModel();
  errors: Map<string, string> = new Map<string, string>();


  public constructor() {
    this.errors = new Map<string, string>();
    this.customerModel = new customerModel();
  }

  static cloneBase(customerOrErrors: customerOrErrorsModel): customerOrErrorsModel {
    const clonedCustomerOrErrors: customerOrErrorsModel = new customerOrErrorsModel();
    clonedCustomerOrErrors.customerModel = customerOrErrors.customerModel;
    clonedCustomerOrErrors.errors = customerOrErrors.errors;
    return clonedCustomerOrErrors;
  }
}
