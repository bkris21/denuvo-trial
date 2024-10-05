import { Component, Input, NgModule } from '@angular/core';
import { NgForm } from '@angular/forms';
import {MatButtonModule} from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import { Customer } from './customer';
import { Project } from './project';
import {HttpClient} from '@angular/common/http'
import { CommonModule } from '@angular/common';

import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {FormsModule} from '@angular/forms';
import {MatIconModule} from '@angular/material/icon';
import {MatDividerModule} from '@angular/material/divider';

@Component({
  selector: 'app-customers-display',
  templateUrl: './customers-display.component.html',
  styleUrls: ['./customers-display.component.css'],
  standalone: true,
  imports: [MatButtonModule, MatCardModule, CommonModule,MatIconModule, MatInputModule,MatFormFieldModule,FormsModule,MatDividerModule]

})
export class CustomersDisplayComponent {

  @Input() customer: Customer = new Customer(0, "", "", new Date() , [new Project(0, "", "", new Date())] )

  customers: Customer[] = [];
  showProjects: boolean[] =[];
  constructor(private http: HttpClient){}

  ngOnInit(): void {
    this.http.get<Customer[]>(
      "http://localhost:8080/api/customers/projects"
    ).subscribe(data => {
      this.customers = data;
      this.showProjects = new Array(data.length).fill(false);
    });
  }

  openProjects(i:number): void{
    this.showProjects[i] = !this.showProjects[i];
  }
}
