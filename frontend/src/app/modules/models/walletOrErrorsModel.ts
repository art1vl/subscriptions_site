import {WalletModel} from "./walletModel";

export class WalletOrErrorsModel {
  walletModel: WalletModel = new WalletModel();
  errors: Map<string, string> = new Map<string, string>();
}
