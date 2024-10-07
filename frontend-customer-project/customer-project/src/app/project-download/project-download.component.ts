import { Component, EventEmitter, Output, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {FormsModule} from '@angular/forms';
import {MatIconModule} from '@angular/material/icon';
import {MatDividerModule} from '@angular/material/divider';
import {MatButtonModule} from '@angular/material/button';

@Component({
  selector: 'app-project-download',
  templateUrl: './project-download.component.html',
  styleUrls: ['./project-download.component.css'],
  standalone: true,
  imports: [MatFormFieldModule, MatInputModule, FormsModule, MatIconModule, MatDividerModule, MatButtonModule]
})
export class ProjectDownloadComponent {
  @ViewChild("dateForm") dateForm!: NgForm;

  constructor(private http: HttpClient){}

  onDownload(){
    const {from, to} = this.dateForm.value;

    const url = `http://localhost:8080/api/customers/projects/zip?startDate=${from}&endDate=${to}`;
    this.http.get(url, {responseType:'blob'}).subscribe((response: Blob)=>{
      const blob = new Blob([response], { type: 'application/zip' });
      const url = window.URL.createObjectURL(blob);
      const a = document.createElement('a');
      a.href = url;
      a.download = 'file.zip'; 
      a.click();
      window.URL.revokeObjectURL(url);
    })
  }

}
