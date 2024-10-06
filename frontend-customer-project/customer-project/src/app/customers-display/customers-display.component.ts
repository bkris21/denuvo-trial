import { Component, Input, Output, EventEmitter} from '@angular/core';
import { NgForm } from '@angular/forms';
import {MatButtonModule} from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import { Customer } from './customer';
import { Project, ProjectForOutput } from './project';
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
  @Output() newDataEvenet = new EventEmitter();
  @Output() projectDeleted  = new EventEmitter<number>();

  customers: Customer[] = [];
  showProjects: boolean[] =[];
  editMode: boolean[][] =[]
  constructor(private http: HttpClient){}

  ngOnInit(): void {
    this.http.get<Customer[]>(
      "http://localhost:8080/api/customers/projects"
    ).subscribe(data => {
      this.customers = data;
      this.showProjects = new Array(data.length).fill(false);
      this.editMode = data.map(c => new Array(c.projects.length).fill(false))
    });
  }

  openProjects(i:number): void{
    this.showProjects[i] = !this.showProjects[i];
  }

  editProject(i: number, j: number):void{
    this.editMode[i][j] = true;
  }

  cancelEdit(i: number, j: number):void{
    this.editMode[i][j] = false;
  }

  updateProject(i: number, j: number) : void{
    const updatedProject = this.customers[i].projects[j];
    const projectRequest = new ProjectForOutput(updatedProject.name, updatedProject.description);
    this.http.put<ProjectForOutput>(
      "http://localhost:8080/api/customer/project/" + updatedProject.id,
      projectRequest
    ).subscribe(
      data => this.newDataEvenet.emit(data)
    );
    this.editMode[i][j] = false;
  }

  deleteProject(id: number):void{
      this.http.delete(
        "http://localhost:8080/api/customer/project/"+id
      ).subscribe(
        ()=>{   
          this.customer.projects = this.customer.projects.filter(p => p.id !== id);
          this.projectDeleted.emit(id);
          window.location.reload();
        }
      );
  }
}
