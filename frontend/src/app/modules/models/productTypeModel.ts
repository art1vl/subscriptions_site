export class ProductTypeModel {
  idProductType: number;
  typeName: string;

  constructor(idProductType: number, typeName: string) {
    this.idProductType = idProductType;
    this.typeName = typeName;
  }
}
