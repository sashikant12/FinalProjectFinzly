import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from 'src/home/home.component';
import { LoginComponent } from 'src/login/login.component';
import { PaidComponent } from './paid/paid.component';
import { UnPaidComponent } from './un-paid/un-paid.component';
import { ChoisePaymentComponent } from './choise-payment/choise-payment.component';
import { InvoiceComponent } from './invoice/invoice.component';
import { ConformPaymentComponent } from './conform-payment/conform-payment.component';

const routes: Routes = [

{
  path:"" , component:LoginComponent
},
{
  path:"Home/:userId" , component:HomeComponent
},
{
  path:"paid-bills/:userId" , component:PaidComponent
},
{
  path:"unPaid" , component:UnPaidComponent
},
{
  path:"ChoisePayment" , component:ChoisePaymentComponent
},
{
path:"Invoice",component:InvoiceComponent
},
{
  path:"ConformPayment",component:ConformPaymentComponent
}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
