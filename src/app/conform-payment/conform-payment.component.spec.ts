import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConformPaymentComponent } from './conform-payment.component';

describe('ConformPaymentComponent', () => {
  let component: ConformPaymentComponent;
  let fixture: ComponentFixture<ConformPaymentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConformPaymentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ConformPaymentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
