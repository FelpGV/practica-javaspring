import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {
  constructor(public router: Router) { }


  isOpen: boolean = false;
  // showDropdown: boolean = false;


  DropdownComponent() {
    this.isOpen = false;
  }

  goToHomePage() {
    this.router.navigateByUrl('/redirect', { skipLocationChange: true }).then(() =>
      this.router.navigate([''])
    );
  }

  goToCategory(category: string) {
    this.router.navigateByUrl('/redirect', { skipLocationChange: true }).then(() =>
      this.router.navigate([`products/${category}`])
    );
    this.isOpen = false;
  }

  openMenu(isOpen: boolean) {
    this.isOpen = isOpen;
  }
}
