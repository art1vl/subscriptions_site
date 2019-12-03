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

  static cloneBase(product: ProductModel): ProductModel {
    const clonedProduct: ProductModel = new ProductModel();
    clonedProduct.id = product.id;
    clonedProduct.companyId = product.companyId;
    clonedProduct.companyName = product.companyName;
    clonedProduct.description = product.description;
    clonedProduct.image = product.image;
    clonedProduct.productName = product.productName;
    clonedProduct.type = product.type;
    clonedProduct.realiseDate = product.realiseDate;
    clonedProduct.cost = product.cost;
    return clonedProduct;
  }
}
