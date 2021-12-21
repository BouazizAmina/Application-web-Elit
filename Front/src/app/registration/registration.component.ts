import { Component, OnInit } from '@angular/core';
import {Recepteur} from "../class/recepteur";
import {RegistrationService} from "../service/registration.service";
import {Router} from "@angular/router";
import {NgForm} from "@angular/forms";
import {LoginComponent} from "../login/login.component";
import {NgbActiveModal, NgbModal} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  recepteur = new Recepteur();
  msg='';

  constructor(private _service:RegistrationService, private _router:Router,private modalService: NgbModal, public activeModal: NgbActiveModal) { }

  ngOnInit(): void {
  }

  registerUser(){
    this._service.registerUserFromRemote(this.recepteur).subscribe(
      data =>{
        this.msg="Inscription réussi";
        this._router.navigate(['/principale']);
          this.activeModal.close('Close click')
      },
      error =>{
        this.msg="Email ou nom d'utilisateur existe déjà";
      }
    )
  }

  open() {
    this.modalService.open(LoginComponent);
  }
}
