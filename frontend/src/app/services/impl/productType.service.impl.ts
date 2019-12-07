import {Injectable} from "@angular/core";
import {ProductTypeService} from "../productType.service";
import {Observable} from "rxjs";
import {ProductTypeModel} from "../../modules/models/productTypeModel";
import {HttpClient} from "@angular/common/http";

@Injectable()
export class ProductTypeServiceImpl implements ProductTypeService{
  constructor(private http: HttpClient) {
  }

  findTypes(): Observable<ProductTypeModel[]> {
    return this.http.get<ProductTypeModel[]>('/api/product/type');
  }
}
