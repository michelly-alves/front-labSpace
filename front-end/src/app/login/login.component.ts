import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../login.service';
import { StorageService } from '../storage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  email: string = '';
  password: string = '';
  loginFailMessage: string = '';
  showModal: boolean = false;

  constructor(private loginService: LoginService,
    private router: Router,
    private localStorage: StorageService) { }

  ngOnInit(): void {
  }

  login(){
    console.log('login - ' + this.email + ':' + this.password);
    this.loginService.login(this.email, this.password).subscribe((user) => {
      const userDetails = {
        email: this.email
      };
      localStorage.setItem('userDetails', JSON.stringify(userDetails));
      console.log('USER/ME: ');
      console.log(user);
      this.localStorage.set('authorization', btoa(this.email + ':' + this.password));
      this.loginFailMessage = ''; // Limpa a mensagem de erro em caso de sucesso
      this.showModal = false; // Esconde o modal em caso de sucesso
      this.router.navigate(['/home']);
    }, (error) => {
      this.loginFailMessage = 'Usuário ou senha inválidos. Tente novamente';
      this.showModal = true; // Mostra o modal em caso de falha
    })

   
  }

  Cadastro(){
    this.router.navigate(['/register']);
  };
 closeModal() {
      this.showModal = false;
    }
}

  