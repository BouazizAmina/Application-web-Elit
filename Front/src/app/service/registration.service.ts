import { Injectable } from '@angular/core';
import {Recepteur} from "../class/recepteur";
import {Observable} from "rxjs";
import {HttpParams, HttpClient, HttpHeaders} from "@angular/common/http";
import {Recepteurlogin} from "../class/recepteurlogin";
import {applySourceSpanToExpressionIfNeeded} from "@angular/compiler/src/output/output_ast";

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  constructor(private http: HttpClient) { }



  public loginUserFromRemote(recepteurlogin:Recepteurlogin):Observable<any>{
    let params = new HttpParams();
    params = params.set('username', recepteurlogin.username);
    params = params.set('password', recepteurlogin.password);
    console.log(params.get('username'));
    return this.http.get<any>("http://localhost:8088/api/auth/signin/",{params: params});
  }

  public registerUserFromRemote(recepteur:Recepteur):Observable<any>{
    return this.http.post<any>("http://localhost:8088/api/auth/signup",recepteur,{observe:'response'});
  }
}
