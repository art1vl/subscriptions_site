import { NgModule } from "@angular/core";
import {HeaderModule} from "../header/header.module";
import {RegistrationPageComponent} from "./components/registration-page.component";
import {CustomerServiceImpl} from "../../services/impl/customer.service.impl";


@NgModule({
  declarations: [
    RegistrationPageComponent
  ],
  imports: [
    HeaderModule
  ],
  providers: [CustomerServiceImpl],
  exports: [RegistrationPageComponent]
})
export class SignInModule {}
