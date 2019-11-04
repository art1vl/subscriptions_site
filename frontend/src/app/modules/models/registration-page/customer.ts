export class Customer {
  id: number;
  name: string;
  surname: string;
  age: number;
  email: string;
  password: string;

  static cloneBase(customer: Customer): Customer {
    const clonedCostumer: Customer = new Customer();
    clonedCostumer.id = customer.id;
    clonedCostumer.name = customer.name;
    clonedCostumer.surname = customer.surname;
    clonedCostumer.age = customer.age;
    clonedCostumer.email = customer.email;
    clonedCostumer.password = customer.password;
    return clonedCostumer;
  }
}
