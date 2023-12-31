import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CustomerService } from '../customer.service';

@Component({
  selector: 'app-conform-payment',
  templateUrl: './conform-payment.component.html',
  styleUrls: ['./conform-payment.component.css']
})
export class ConformPaymentComponent implements OnInit {
  userId:any=sessionStorage.getItem("customerId");
  selectedBillId:any=sessionStorage.getItem("billId");
  paymnetMethod:any=sessionStorage.getItem("paymentModeId");
  Cid: number | undefined;
  OTP: number| undefined;
  min = 100000;
  max = 999999;
  randomInteger = Math.floor(Math.random() * (this.max - this.min + 1)) + this.min;
  showOTPInput: boolean=false;
  isOTPValid: boolean = false;
  constructor(private customerService : CustomerService,private router:Router) { }

  showCreditCard = false;
  showDebitCard = false;
    personName: string = '';
    cardNumber: string = '';
    expiry: string = '';
    cvv: string = '';
  
    get isFormValid(): boolean {
      // Check if all fields are filled
      if(this.personName =='' && this.cardNumber=='' && this.expiry=='' && this.cvv=='')
      return false;
       else
       return true; 
    }
  
    processPayment() {
      if (this.isFormValid) {
        // Perform payment processing logic here
        // This is where you can send payment details to a server or perform any other actions
        console.log('Payment processed successfully');
        this.router.navigate(['./Home/:{this.Cid}']);
      } else {
        console.log('Please fill in all fields before making a payment');
      }
    }
  GoBackToHome(){
    
  }
  ngOnInit(): void {
    this.ConformPayment(this.userId,this.selectedBillId);
  }


  // ConformPayment
  ConformPayment(userId:any,SelectedBill:any) {
    this.customerService.ConformPayment(this.userId,this.selectedBillId).subscribe((response:any) => {
      console.log(response); 
      
    });
}

}
