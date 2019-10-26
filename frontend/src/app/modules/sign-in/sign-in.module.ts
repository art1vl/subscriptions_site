import { NgModule } from "@angular/core";
import {SignInComponent} from "./components/sign-in.component";
import {HeaderModule} from "../header/header.module";


@NgModule({
  declarations: [
    SignInComponent
  ],
  imports: [
    HeaderModule
  ],
  providers: [],
  exports: [SignInComponent]
})
export class SignInModule {}
