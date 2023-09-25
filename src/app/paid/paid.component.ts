import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CustomerService } from '../customer.service';
import { CustomerBill } from '../customer-bill';

@Component({
  selector: 'app-paid',
  templateUrl: './paid.component.html',
  styleUrls: ['./paid.component.css']
})
export class PaidComponent implements OnInit {
  
  userId:any=sessionStorage.getItem("customerId");
  paidBills!:CustomerBill[];
  constructor(    private route: Router,
    private customerService : CustomerService
) { }

  ngOnInit(): void 
  {
      this.loadPaidBills(this.userId);
  

}


loadPaidBills(userId:any) {
  this.customerService.loadPaidBills(this.userId).subscribe((response) => {
    console.log(response); 
    this.paidBills=response; 
  });
}
}
