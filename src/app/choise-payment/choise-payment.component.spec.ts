import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChoisePaymentComponent } from './choise-payment.component';

describe('ChoisePaymentComponent', () => {
  let component: ChoisePaymentComponent;
  let fixture: ComponentFixture<ChoisePaymentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ChoisePaymentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ChoisePaymentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
