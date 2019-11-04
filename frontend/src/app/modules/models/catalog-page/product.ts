export class Product {
  id: number;
  company: string;
  productName: string;
  description: string;
  image: string;

  static cloneBase(product: Product): Product {
    const clonedProduct: Product = new Product();
    clonedProduct.id = product.id;
    clonedProduct.company = product.company;
    clonedProduct.description = product.description;
    clonedProduct.image = product.image;
    clonedProduct.productName = product.productName;
    return clonedProduct;
  }
}
