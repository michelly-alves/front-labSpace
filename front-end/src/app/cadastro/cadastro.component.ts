import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cadastro',
  templateUrl: './cadastro.component.html',
  styleUrls: ['./cadastro.component.css']
})
export class CadastroComponent {
  matricula: string = '';
  email: string = '';
  password: string = '';
  confirmPassword: string = '';
  isAluno: boolean = false;
  isProfessor: boolean = false;

  constructor(private router: Router, private http: HttpClient) {}

  register() {
    if (this.password !== this.confirmPassword) {
      console.error('As senhas não correspondem.');
      alert("As senhas não correspondem");
      return;
    }
    if (this.isAluno && this.isProfessor) {
      alert("Você não pode selecionar ambos Aluno e Professor. Selecione apenas um.");
      return;
    }
    
    if (!this.isAluno && !this.isProfessor) {
      alert("Você precisa selecionar se é Aluno ou Professor.");
      return;
    }
//atributos p mandar pro back: this.o que tava no html
    const user = {
      matricula: this.matricula,
      senha: this.password,
      email: this.email
    };

    this.http.post('http://localhost:8080/api/usuario/register', user)
      .subscribe(
        response => {
          console.log('Cadastro realizado com sucesso', response);
          this.router.navigate(['/login']);
        },
        error => {
          console.error('Erro ao cadastrar usuário', error);
        }
      );
  }
}
