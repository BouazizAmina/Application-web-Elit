import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {Cotisation} from "../class/cotisation";
import {Remboursement} from "../class/remboursement";
import {LoginComponent} from "../login/login.component";
import {DeconnexionComponent} from "../deconnexion/deconnexion.component";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-pageprincipale',
  templateUrl: './pageprincipale.component.html',
  styleUrls: ['./pageprincipale.component.css']
})
export class PageprincipaleComponent implements OnInit {
  matricule: string = '';
  taux: string ='';
  id: number = 0;
  donne: string = '';
  show : boolean = false ;
  show2 : boolean = false ;
  show01 : boolean = false ;
  show02 : boolean = false ;
  exist : boolean = false;
  findCotisationbyTaux : Cotisation[] = [];
  findRecomponsebyTypere : Remboursement[] = [];
  msg='';
  msg21='';
  msg22='';
  msg23='';
  msg24='';
  constructor(private router:Router,private http: HttpClient,private modalService: NgbModal) { }

  ngOnInit(): void {
  }

  open() {
    this.modalService.open(DeconnexionComponent);
  }

  copier1(){
    navigator.clipboard.writeText('123');
    this.msg21="Matricule copié: 123";
    this.msg22='';
    this.msg23='';
    this.msg24='';
  }
  copier2(){
    navigator.clipboard.writeText('147');
    this.msg22="Matricule copié:147 ";
    this.msg21='';
    this.msg23='';
    this.msg24='';
  }
  copier3(){
    navigator.clipboard.writeText('256');
    this.msg23="Matricule copié: 256";
    this.msg22='';
    this.msg21='';
    this.msg24='';
  }
  copier4(){
    navigator.clipboard.writeText('763');
    this.msg24="Matricule copié: 763";
    this.msg22='';
    this.msg23='';
    this.msg21='';
  }

  scrollFilter(el:HTMLElement){
    el.scrollIntoView();
  }
  scrollFilter2(el:HTMLElement){
    if(this.taux=='cotisation'){this.show01=true; this.show02=false,this.taux=''}
    else {this.show02=true; this.show01=false,this.taux=''}
    el.scrollIntoView();
  }
  scrollMatricule(el:HTMLElement){
    // if(this.matricule =="796" || this.matricule =="963") {
    //   this.show2=true;
    //   el.scrollIntoView();
    //   this.msg='';
    //   this.taux='';
    // }
    // else {
    if( this.matricule != "123" && this.matricule != "147"&&this.matricule !="256" &&this.matricule !="763" ){
      this.msg='ce matricule n\'existe pas';
      this.show=false;
      this.show2=false;}
    else {
        this.show=true;
        el.scrollIntoView();
        this.msg='';
        this.show2=false;
        this.msg21='';this.msg22='';this.msg23='';this.msg24='';
        this.show01=false; this.show02=false
    }
  }
  scrollDonnees(el:HTMLElement){
    if( (this.id==0 || this.id>16 ) && this.matricule!="256" &&(this.taux !="7895") &&(this.taux!= "12454") &&(this.taux!= "7777") &&(this.taux!= "1234") &&(this.taux!= 'infine') &&(this.taux!= 'par anticipation')) {
      if(this.matricule=="147"){this.msg='Il n\'existe pas une cotisation avec cet ID';}
      else {this.msg='Il n\'existe pas des cotisations avec ce taux';}
      this.show2=false;
    }
    else {
      this.show2=true;
      el.scrollIntoView();
      this.msg='';
    }
  }

  ChoisirWS(){
    switch (this.matricule) {
      case "123":
        this.http.post<Cotisation[]>("http://localhost:8088/api/auth/FindCotisationbyTaux",this.taux).subscribe(
          data =>{
            this.findCotisationbyTaux=data;
          },
          error =>{
            console.log('erreur')}
        )
        break;
      case "147":
        this.http.post<any>("http://localhost:8088/api/auth/FindTauxCotisationbyId",this.id).subscribe(
          data =>{
            this.donne=data;
            console.log(data);
          },
          error =>{
            console.log("erreur");
          }
        )
        break;
      case "256":
        this.http.post<any>("http://localhost:8088/api/auth/ExistCotisationbyTaux",this.taux).subscribe(
          data =>{
            this.exist=data;
            console.log(data);
          },
          error =>{
            console.log("erreur")
          }
        )
        break;
      case "763":
        this.http.post<Remboursement[]>("http://localhost:8088/api/auth/FindRecomponsebyTypere",this.taux).subscribe(
          data =>{
            this.findRecomponsebyTypere=data;
            console.log(this.findRecomponsebyTypere);
          },
          error =>{
            console.log("erreur")}
        )
        break;
      // case "796":
      //   this.http.get<Cotisation[]>("http://localhost:8088/api/auth/ListCotisation").subscribe(
      //     data =>{
      //       this.findCotisationbyTaux=data;
      //       console.log(data);
      //     },
      //     error =>{
      //       console.log("erreur")}
      //   )
      //   break;
      // case "963":
      //   this.http.get<Remboursement[]>("http://localhost:8088/api/auth/ListRecomposation").subscribe(
      //     data =>{
      //       this.findRecomponsebyTypere=data;
      //       console.log(data);
      //     },
      //     error =>{
      //       console.log("erreur")}
      //   )
      //   break;
    }
  }
}
