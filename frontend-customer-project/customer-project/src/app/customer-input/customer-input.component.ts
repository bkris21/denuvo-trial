import { Component, EventEmitter, Output, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {FormsModule} from '@angular/forms';
import {MatIconModule} from '@angular/material/icon';
import {MatDividerModule} from '@angular/material/divider';
import {MatButtonModule} from '@angular/material/button';
import { CustomerForInput } from './customer-input';
import { ProjectForInput } from './project-input';

@Component({
  selector: 'app-customer-input',
  templateUrl: './customer-input.component.html',
  styleUrls: ['./customer-input.component.css'],
  standalone: true,
  imports: [MatFormFieldModule, MatInputModule, FormsModule, MatIconModule, MatDividerModule, MatButtonModule]
})
export class CustomerInputComponent {
  @ViewChild("customerForm") customerForm!: NgForm;
  
  @Output() newDataEvenet = new EventEmitter();

  constructor(private http: HttpClient){}

      onSubmit(): void {
         const formValue = this.customerForm.value;
         const project = new ProjectForInput(formValue.projectName , formValue.description);
         const customer = new CustomerForInput(formValue.name, formValue.contact, project);


        this.http.post<CustomerForInput>(
          "http://localhost:8080/api/customer/project",
          customer
        ).subscribe(data => this.newDataEvenet.emit(data))
      }

  }
