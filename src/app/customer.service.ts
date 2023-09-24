import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class CustomerService {
  PaymentMethod(userId: any, selectedBillId: any, paymnetMethod: any) {
    throw new Error('Method not implemented.');
  }
baseUrl:string="http://localhost:8080/"
  constructor(private customerHttp : HttpClient) { }

  login(id:any){
    return (this.customerHttp.get(`${this.baseUrl}Validation/${id}`));
  }

  getallBill(){
    return this.customerHttp.get(`${this.baseUrl}getAlltranction`);
  
  }

  loadPaidBills (userId: number): Observable<any> {
    return this.customerHttp.get(`${this.baseUrl}getAlltranctionByIdForSucess/${userId}`);
  }

  loadUnAllPaidBills(userId: number): Observable<any> {
    return this.customerHttp.get(`${this.baseUrl}getAlltranctionByIdForPending/${userId}`);
  }

  
  InvoiceComponent(userId:number,SelectedBill:number):Observable<any> {
    return this.customerHttp.get(`${this.baseUrl}getAlltranctionBillInvoice/${userId}/${SelectedBill}`);
  }
  PaymentMethodMode(userId:number,SelectedBill:number,paymnetMethod:number):Observable<string> {
    return this.customerHttp.get<string>(`${this.baseUrl}setAlltranctionByIdAndPaymentId/${userId}/${SelectedBill}/${paymnetMethod}`);
  }
  ConformPayment(userId:number,SelectedBill:number){
    return this.customerHttp.get<string>(`${this.baseUrl}getAlltranctionPaymentStatusUpdate/${userId}/${SelectedBill}`);
  }
  
}
