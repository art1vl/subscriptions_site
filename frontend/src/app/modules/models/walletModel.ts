export class WalletModel {
  idWallet?: number;
  balance: number;
  debt: number;
  cardNumber: number;
  cardDate: Date;
  cardCvvCode: number;
  personName: string;

  static cloneBase(wallet: WalletModel): WalletModel {
    const clonedWallet: WalletModel = new WalletModel();
    clonedWallet.idWallet = wallet.idWallet;
    clonedWallet.balance = wallet.balance;
    clonedWallet.cardNumber = wallet.cardNumber;
    clonedWallet.cardDate = wallet.cardDate;
    clonedWallet.cardCvvCode = wallet.cardCvvCode;
    clonedWallet.personName = wallet.personName;
    return clonedWallet;
  }
}
