import { Component, OnInit } from '@angular/core';
import { CustomerBill } from '../customer-bill';
import { CustomerService } from '../customer.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-invoice',
  templateUrl: './invoice.component.html',
  styleUrls: ['./invoice.component.css']
})
export class InvoiceComponent implements OnInit {
  userId:any=sessionStorage.getItem("customerId");
  selectedBillId:any=sessionStorage.getItem("billId");
  paymnetMethod:any=sessionStorage.getItem("paymentModeId");
  Generate!:CustomerBill[];
  invoiceItems!:CustomerBill[];
  // customerService: any;
  constructor(private customerService : CustomerService,private router:Router) { }
  navigateToConformPayment():void{
    this.router.navigate(['ConformPayment']);
  }

  

  ngOnInit(): void {
    this.InvoiceComponent(this.userId,this.selectedBillId,this.paymnetMethod);
    this.checkIdentity(this.userId,this.selectedBillId)
  }

  InvoiceComponent(userId:any,SelectedBill:any,paymnetMethod:any) {
    this.customerService.PaymentMethodMode(this.userId,this.selectedBillId,this.paymnetMethod).subscribe((response:any) => {
      console.log(response); 
      // console.log("tha par hu mai dhek le na");  
    });
  }
    checkIdentity(userId:any,selectedBillId:any){
      this.customerService.InvoiceComponent(this.userId,this.selectedBillId).subscribe((response:any) => {
        console.log(response);
        this.Generate=response;
        this.invoiceItems=response;
        // console.log("tha par hu mai dhek le na thora niche");  
    });

}
}