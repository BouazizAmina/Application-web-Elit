import {Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import {LoginComponent} from "../login/login.component";

declare let $: any;
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  constructor(private modalService: NgbModal) { }

  ngOnInit(): void {
  }

  homeSlider={items:1, dots: true};

  open() {
    this.modalService.open(LoginComponent);
  }
}
