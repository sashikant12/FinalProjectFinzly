import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from 'src/login/login.component';
import{HttpClientModule} from '@angular/common/http'
import{FormsModule} from '@angular/forms';
import { HomeComponent } from '../home/home.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HeaderComponent } from '../header/header.component';
import { PaidComponent } from './paid/paid.component';
import { UnPaidComponent } from './un-paid/un-paid.component';
import { CustomerDetailsComponent } from './customer-details/customer-details.component';
import { InvoiceComponent } from './invoice/invoice.component';
import { ChoisePaymentComponent } from './choise-payment/choise-payment.component';
import { ConformPaymentComponent } from './conform-payment/conform-payment.component'
@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    HeaderComponent,
    PaidComponent,
    UnPaidComponent,
    CustomerDetailsComponent,
    InvoiceComponent,
    ChoisePaymentComponent,
    ConformPaymentComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
