import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomersDisplayComponent } from './customers-display.component';

describe('CustomersDisplayComponent', () => {
  let component: CustomersDisplayComponent;
  let fixture: ComponentFixture<CustomersDisplayComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CustomersDisplayComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CustomersDisplayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
