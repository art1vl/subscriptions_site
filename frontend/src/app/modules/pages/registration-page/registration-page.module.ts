import { NgModule } from "@angular/core";
import {RegistrationPageComponent} from "./components/registration-page.component";
import {RouterModule} from "@angular/router";
import {ReactiveFormsModule} from "@angular/forms";
import {CommonModule} from "@angular/common";


@NgModule({
  declarations: [
    RegistrationPageComponent
  ],
  imports: [
    RouterModule,
    ReactiveFormsModule,
    CommonModule
  ],
  providers: [],
  exports: [RegistrationPageComponent]
})
export class RegistrationPageModule {}
