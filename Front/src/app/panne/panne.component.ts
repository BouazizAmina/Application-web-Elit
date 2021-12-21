import { Component, OnInit } from '@angular/core';
import {DeconnexionComponent} from "../deconnexion/deconnexion.component";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {Cotisation} from "../class/cotisation";
import {Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {Panne} from "../class/panne";

@Component({
  selector: 'app-panne',
  templateUrl: './panne.component.html',
  styleUrls: ['./panne.component.css']
})
export class PanneComponent implements OnInit {
  panne1=new Panne();
  msg='';
  constructor(private modalService: NgbModal,private router:Router,private http: HttpClient) { }

  ngOnInit(): void {
  }
  open() {
    this.modalService.open(DeconnexionComponent);
  }

  declarerPanne(){
    this.msg="Votre message est bien reçu";
    this.http.post<Panne[]>("http://localhost:8088/api/auth/AddPanne",this.panne1).subscribe(
      data =>{
        console.log(data);
      },
      error =>{
        console.log('erreur');}
    )
  }

}
