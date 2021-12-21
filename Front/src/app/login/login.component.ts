import {Component, OnInit} from '@angular/core';
import {NgForm} from '@angular/forms';
import {RegistrationService} from "../service/registration.service";
import {Recepteur} from "../class/recepteur";
import {Router} from "@angular/router";
import {Recepteurlogin} from "../class/recepteurlogin";
import {NgbActiveModal, NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {RegistrationComponent} from "../registration/registration.component";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  recepteurlogin = new Recepteurlogin();
  msg='';
  show: boolean = false;
  constructor(private service:RegistrationService, private router:Router,private modalService: NgbModal, public activeModal: NgbActiveModal) { }

  ngOnInit(): void {
  }

  loginUser() {
    this.service.loginUserFromRemote(this.recepteurlogin).subscribe(
      data =>{
        console.log("reponse received"),
          this.show=true,
          this.activeModal.close('Cross click'),
        this.router.navigate(['/principale'])

      },
        error =>{
        console.log("exception occured"),
        this.msg="Entrer un nom d'utilisateur et mot de passe valides",
          this.show=false
      }
    )
  }


  open() {
    this.modalService.open(RegistrationComponent);
  }
}
