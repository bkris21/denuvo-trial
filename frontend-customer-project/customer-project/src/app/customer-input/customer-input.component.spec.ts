import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerInputComponent } from './customer-input.component';

describe('CustomerInputComponent', () => {
  let component: CustomerInputComponent;
  let fixture: ComponentFixture<CustomerInputComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CustomerInputComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CustomerInputComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
