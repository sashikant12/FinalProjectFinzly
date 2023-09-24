import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {
  constructor(private router: Router) {}

  navigateToBill(event: Event): void {
    const selectedOption = (event.target as HTMLSelectElement).value;
    switch (selectedOption) {
      case 'paid':
        this.router.navigate(['paid-bills/:userId']);
        break;
      case 'unpaid':
        this.router.navigate(['/unPaid']);
        break;
      case 'all':
        this.router.navigate(['/all']);
        break;
      default:
        // Do nothing or handle the default case as needed
        break;
    }
  }
}
