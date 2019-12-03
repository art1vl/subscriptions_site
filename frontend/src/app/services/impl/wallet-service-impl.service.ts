import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {WalletService} from "../wallet.service";
import {Observable} from "rxjs";
import {WalletModel} from "../../modules/models/walletModel";
import {WalletOrErrorsModel} from "../../modules/models/walletOrErrorsModel";

@Injectable()
export class WalletServiceImpl implements WalletService {

  constructor(private http: HttpClient) {
  }

  deleteCard(id: number): Observable<void> {
    console.log(id);
    return this.http.delete<void>('/api/wallet/' + id);
  }

  replenishCard(wallet: WalletModel): Observable<WalletOrErrorsModel> {
    return this.http.put<WalletOrErrorsModel>('/api/wallet/replenish', wallet);
  }


}
