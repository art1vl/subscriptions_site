import {Observable} from "rxjs";
import {ProductTypeModel} from "../modules/models/productTypeModel";

export interface ProductTypeService {
  findTypes(): Observable<ProductTypeModel[]>
}
