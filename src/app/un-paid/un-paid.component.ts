import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CustomerService } from '../customer.service';
import { CustomerBill } from '../customer-bill';

@Component({
  selector: 'app-un-paid',
  templateUrl: './un-paid.component.html',
  styleUrls: ['./un-paid.component.css']
})
export class UnPaidComponent implements OnInit {
  userId:any=sessionStorage.getItem("customerId");
  SelectedBill!:number;
  notinuse!:number;
  unPaidBills!:CustomerBill[];
  constructor(private customerService : CustomerService,private router:Router) { }
  
  
  navigateToChoicePayment(billId: number): void {
    this.SelectedBill=billId;
    sessionStorage.setItem("billId", ""+ this.SelectedBill);

    // Navigate to the ChoicePaymentComponent when the "Pay Now" button is clicked
    this.router.navigate(['ChoisePayment']);
  }

  ngOnInit(): void {
    this.loadUnPaidBills(this.userId);
  }

  loadUnPaidBills(userId:any) {
    this.customerService.loadUnAllPaidBills(this.userId).subscribe((response) => {
      console.log(response); 
      this.unPaidBills=response;
      
      sessionStorage.setItem('selectedBillId', ""+ this.notinuse);
      // Store the fetched paid bills data
      console.log(this.SelectedBill);
      // console.log(this.notinuse);
      // console.log("tha par hu mai edhar par lore"); 
    });

    
  }
}
