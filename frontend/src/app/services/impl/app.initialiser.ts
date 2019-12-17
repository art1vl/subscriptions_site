import {CustomerServiceImpl} from "./customer.service.impl";
import {HttpClient} from "@angular/common/http";
import {CompanyServiceImpl} from "./company.service.impl";
import {CustomerOrCompanyOrAdminModel} from "../../modules/models/customerOrCompanyOrAdminModel";
import {AdminServiceImpl} from "./admin.service.impl";

export function initApp(http: HttpClient, customerService: CustomerServiceImpl, companyService: CompanyServiceImpl,
                        adminService: AdminServiceImpl) {
  return () => {
    if (localStorage.getItem('token') != null) {
      return http.get<CustomerOrCompanyOrAdminModel>('/api/logInInf/' + localStorage.getItem('token'))
        .toPromise()
        .then((resp) => {
          customerService.customer = resp.customerModel;
          companyService.company = resp.companyModel;
          adminService.admin = resp.adminModel;
        }, errorResponse => {
          if (errorResponse.status == '500') {
            console.log('lol');
            console.log(errorResponse);
            localStorage.removeItem('token');
          } else {
            console.log('12333', errorResponse);
          }
        });
    }
  }
}


