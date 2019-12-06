import {WalletModel} from "./walletModel";

export class companyModel {
  id?: number;
  name: string;
  idLogInInf: number;
  wallet: WalletModel;
  email: string;
  password: string;
  isActive: number;
}
