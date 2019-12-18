import {ProductTypeModel} from "./productTypeModel";

export class ProductModel {
  id: number;
  companyId: number;
  companyName: string;
  description: string;
  image: string;
  type: ProductTypeModel;
  realiseDate: Date;
  cost: number;
  productName: string;
  isActive: number;
  companyActiveStatus: number;
}
