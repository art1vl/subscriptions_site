import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {AdminService} from "../admin.service";
import {adminModel} from "../../modules/models/adminModel";

@Injectable()
export class AdminServiceImpl implements AdminService {
  admin: adminModel;

  constructor(private http: HttpClient) {
    console.log("new");
  }

}
