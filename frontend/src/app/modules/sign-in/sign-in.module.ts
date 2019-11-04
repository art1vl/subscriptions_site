import { NgModule } from "@angular/core";
import {SignInComponent} from "./components/sign-in.component";
import {HeaderComponent} from "../header/components/header.component";


@NgModule({
  declarations: [
    SignInComponent
  ],
  imports: [
    HeaderComponent
  ],
  providers: [],
  exports: [SignInComponent]
})
export class SignInModule {}
