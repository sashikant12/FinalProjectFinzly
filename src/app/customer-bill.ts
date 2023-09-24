import { Customer } from "./customer";
import { PaymentModes } from "./payment-modes";

export class CustomerBill {
    billId!:number;
    totalAmount!:number;
    amount!:number;
    status!:string;	
	 consumedUnit!:number;
	 month!:number;
	  startDate!:Date;
	 endDate!:Date;
	  customer !:Customer;
	  paymentMode!:PaymentModes;
}

