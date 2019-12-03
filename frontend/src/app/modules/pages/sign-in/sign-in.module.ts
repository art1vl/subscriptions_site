import { NgModule } from "@angular/core";
import {SignInComponent} from "./components/sign-in.component";
import {ReactiveFormsModule} from "@angular/forms";
import {CommonModule} from "@angular/common";
import {RouterModule} from "@angular/router";
import {CustomerServiceImpl} from "../../../services/impl/customer.service.impl";
import {CompanyServiceImpl} from "../../../services/impl/company.service.impl";
import {AdminServiceImpl} from "../../../services/impl/admin.service.impl";
import {LogInInfServiceImpl} from "../../../services/impl/logInInf.service.impl";

@NgModule({
  declarations: [
    SignInComponent
  ],
  imports: [
    ReactiveFormsModule,
    CommonModule,
    RouterModule
  ],
  providers: [
    CustomerServiceImpl,
    CompanyServiceImpl,
    AdminServiceImpl,
    LogInInfServiceImpl
  ],
  exports: [SignInComponent]
})
export class SignInModule {}
