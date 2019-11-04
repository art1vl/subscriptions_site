import { Component, OnInit } from "@angular/core";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: "app-sign-in",
  templateUrl: "./sign-in.component.html",
  styleUrls: ["./sign-in.component.css"]
})
export class SignInComponent implements OnInit {

  myForm : FormGroup;
  constructor(){
    this.myForm = new FormGroup({

      "userEmail": new FormControl("", [
        Validators.required,
        Validators.email
      ]),
      "userPassword": new FormControl("", [
        Validators.required,
        Validators.minLength(8)
      ])
    });
  }

  submit(){
    console.log(this.myForm);
  }

  func() {
    console.log(this.myForm);
  }

  ngOnInit() {

  }
}
