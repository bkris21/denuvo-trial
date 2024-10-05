import { Component, Input } from '@angular/core';
import {MatButtonModule} from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import { Customer } from './customer';
import { Project } from './project';

@Component({
  selector: 'app-customers-display',
  templateUrl: './customers-display.component.html',
  styleUrls: ['./customers-display.component.css'],
  standalone: true,
  imports: [MatButtonModule, MatCardModule]

})
export class CustomersDisplayComponent {

  @Input() customer: Customer = new Customer(0, "", "", new Date() , [new Project(0, "", "", new Date())] )

}
