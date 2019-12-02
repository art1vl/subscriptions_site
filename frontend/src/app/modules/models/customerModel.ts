import {WalletModel} from "./walletModel";

export class customerModel {
  id: number;
  name: string;
  surname: string;
  age: number;
  email: string;
  password: string;
  wallet: WalletModel;
  isActive: number;
  idLogInInf: number;

  static cloneBase(customer: customerModel): customerModel {
    const clonedCustomer: customerModel = new customerModel();
    clonedCustomer.name = customer.name;
    clonedCustomer.surname = customer.surname;
    clonedCustomer.age = customer.age;
    clonedCustomer.email = customer.email;
    clonedCustomer.password = customer.password;
    clonedCustomer.wallet = customer.wallet;
    clonedCustomer.isActive = customer.isActive;
    clonedCustomer.idLogInInf = customer.idLogInInf;
    return clonedCustomer;
  }
}
