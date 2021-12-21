import { Injectable } from '@angular/core';

export class Recepteur{
  constructor(
    public id:string,
    public name:string,
    public designation:string,
    public salary:string,
  ) {}

}

@Injectable({
  providedIn: 'root'
})
export class HttpClientService {

  constructor() { }
}
