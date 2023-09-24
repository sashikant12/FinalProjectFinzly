import { Component, OnInit } from '@angular/core';
import { CustomerBill } from '../customer-bill';
import { CustomerService } from '../customer.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-choise-payment',
  templateUrl: './choise-payment.component.html',
  styleUrls: ['./choise-payment.component.css']
})
export class ChoisePaymentComponent implements OnInit {
  userId:any=sessionStorage.getItem("customerId");
  selectedBillId:any=sessionStorage.getItem("billId");
  pay:any;
  selectedPaymentMethod:any;
  Invoice!:CustomerBill[];
  constructor(private customerService : CustomerService,private router:Router) { }

  navigateToInvoice():void{
    // Navigate to the ChoicePaymentComponent when the "Pay Now" button is clicked
    if (this.selectedPaymentMethod === 'wallet') {
      // User selected wallet (value: 'wallet')
      // Perform wallet payment (e.g., set a variable to 2)
       this.pay = 2;
       sessionStorage.setItem("paymentModeId",this.pay);
      console.log('Paid with wallet:', this.pay);
    } else if (this.selectedPaymentMethod === 'online') {
      // User selected online (value: 'online')
      // Perform online payment (e.g., set a variable to 1)
       this.pay = 1;
      console.log('Paid online:', this.pay);
      sessionStorage.setItem("paymentModeId",this.pay);
      console.log('Paid with wallet:', this.pay);
    } else {
      // Handle other cases or invalid selections
      console.error('Invalid payment method');
      return;
    }
  
    // You can now use the paymentValue as needed.
    // For example, you can send it to your backend for processing.
  

  
    this.router.navigate(['Invoice']);
  }
  ngOnInit(): void {
    this.InvoiceComponent(this.userId,this.selectedBillId);
  }
  InvoiceComponent(userId:any,selectedBillId:any) {
    this.customerService.InvoiceComponent(this.userId,this.selectedBillId).subscribe((response:any) => {
      console.log(response);
      console.log(this.selectedPaymentMethod);
      // console.log("qwertyuio")
      console.log(this.pay); 
      this.Invoice=response;
      // console.log("tha par hu mai dhek le na");  
    });
}
}
