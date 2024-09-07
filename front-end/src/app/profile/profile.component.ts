import { Component } from '@angular/core';
import { Router } from '@angular/router';

interface UserDetails {
  matricula: string;
  senha: string;
}
@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent {

  matricula: string = '';
  senha: string = '';
  
  constructor(private router: Router) {}
  usuario: UserDetails | null = null;
  ngOnInit(): void {
    const userDetailsString = localStorage.getItem('userDetails');

    if (userDetailsString) {
      this.usuario = JSON.parse(userDetailsString);
    }
  }
  navigateToMyChampionships() {
    this.router.navigate(['/my-championships']);
  }

  navigateToEditProfile() {
    this.router.navigate(['/edit-profile']);
  }

  deleteAccount() {
    this.router.navigate(['/login']);
  }

  limparLocalStorage(): void {
    localStorage.clear();
    console.log('Local Storage foi limpo!');
    window.location.reload();
  }
}
