import {ProductModel} from "./productModel";

export class ProductOrErrorModel {
  product: ProductModel;
  errors: Map<string, string>;
}
