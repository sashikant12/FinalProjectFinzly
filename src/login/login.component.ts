import { CustomerService } from 'src/app/customer.service';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent  implements OnInit{
  Cid: number | undefined;
  OTP: number| undefined;
  min = 100000;
  max = 999999;
  randomInteger = Math.floor(Math.random() * (this.max - this.min + 1)) + this.min;
  showOTPInput: boolean=false;
  isOTPValid: boolean = false;

  constructor(private customerService:CustomerService,private router:Router) { }





  ngOnInit(): void {
    
    this.getTransaction();
    this.onSubmit();
    
  }
  

  getTransaction(){
    this.customerService.getallBill().subscribe(response=>{
      console.log(response);
  
      
    });
  }
  generateOTP(){
    this.randomInteger= Math.floor(Math.random()* (this.max - this.min + 1)) + this.min;
    console.log(this.randomInteger);
    this.showOTPInput = true;
    this.isOTPValid = true ;
  }

  
  onSubmit() {
    
    return this.customerService.login(this.Cid).subscribe(
      (response) => {
        // Handle the response from the backend as needed
        if(this.OTP== this.randomInteger&&response!=null){
          // console.log("Ho gaya Olle Olle");
          // console.log('Login successful');
          console.log(this.Cid);
          sessionStorage.setItem("customerId",""+this.Cid);
          console.log('API Response:', response);
          this.router.navigate([`./Home/:{this.Cid}`]);
        }else{
          alert(`Invalid Otp and Customer Id`);
          // console.log("Dhek ke dal ");
          window.location.reload();
        }
        
      }
  );
}
}


