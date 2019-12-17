import {Observable} from "rxjs";
import {WalletModel} from "../modules/models/walletModel";
import {WalletOrErrorsModel} from "../modules/models/walletOrErrorsModel";

export interface WalletService {
  deleteCard(id: number): Observable<void>;

  updateCard(wallet: WalletModel): Observable<WalletOrErrorsModel>;


}
