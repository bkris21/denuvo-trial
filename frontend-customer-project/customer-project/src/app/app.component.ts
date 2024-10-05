import { Component } from '@angular/core';
import { Customer } from './customers-display/customer';
import {HttpClient} from '@angular/common/http'


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'customer-project';

  customers: Customer[] = [];

  constructor(private http: HttpClient){}

  ngOnInit(): void {
    this.http.get<Customer[]>(
      "http://localhost:8080/api/customers/projects"
    ).subscribe(data => this.customers = data);
  }

}
