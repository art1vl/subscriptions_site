export class ProductModel {
  id: number;
  company: string;
  description: string;
  image: string;
  type: string;
  realiseDate: Date;
  cost: number;
  productName: string;

  static cloneBase(product: ProductModel): ProductModel {
    const clonedProduct: ProductModel = new ProductModel();
    clonedProduct.id = product.id;
    clonedProduct.company = product.company;
    clonedProduct.description = product.description;
    clonedProduct.image = product.image;
    clonedProduct.productName = product.productName;
    clonedProduct.type = product.type;
    clonedProduct.realiseDate = product.realiseDate;
    clonedProduct.cost = product.cost;
    return clonedProduct;
  }
}
