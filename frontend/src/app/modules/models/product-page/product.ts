export class Product {
  id: number;
  company: string;
  description: string;

  static cloneBase(product: Product): Product {
    const clonedProduct: Product = new Product();
    clonedProduct.id = product.id;
    clonedProduct.company = product.company;
    clonedProduct.description = product.description;
    return clonedProduct;
  }
}

export class BillingAccountStr {
}
