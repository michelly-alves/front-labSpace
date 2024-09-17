import { LoginService } from './../login.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

interface UserDetails {
  id: number;  // Inclua o ID do aluno
  email: string;
  nome: string;
  matricula: string;
  senha?: string;
}

@Component({
  selector: 'app-editar-perfil',
  templateUrl: './editar-perfil.component.html',
  styleUrls: ['./editar-perfil.component.css']
})
export class EditarPerfilComponent implements OnInit {
  nome: string = '';
  matricula: string = '';
  email: string = '';
  password: string = '';
  confirmPassword: string = '';

  constructor(private router: Router, private http: HttpClient, private loginService: LoginService) {}

  ngOnInit() {
    const userDetailsString = localStorage.getItem('userDetails');
    if (userDetailsString) {
      const userDetails = JSON.parse(userDetailsString);
      this.email = userDetails.email || '';
    }
    
    if (this.email) {
      console.log('Buscando usuário com e-mail:', this.email); // Verifique o e-mail no console
      this.loginService.getUserByEmail(this.email).subscribe(
        (response: UserDetails) => {
          console.log('Dados do usuário:', response); // Verifique a resposta
          this.nome = response.nome;
          this.matricula = response.matricula;
          this.email = response.email; // Verifique se o e-mail está sendo atribuído aqui
        },
        error => {
          console.error('Erro ao buscar os dados do aluno', error);
        }
      );
    }
  }
  

  update() {
    if (this.password !== this.confirmPassword) {
      alert("As senhas não correspondem.");
      return;
    }

    const updatedUser = {
      nome: this.nome,
      matricula: this.matricula,
      email: this.email,
      senha: this.password // Somente envia a senha se ela foi modificada
    };

    // Faz a requisição PUT para atualizar os dados do aluno logado
    this.http.put(`http://localhost:8080/api/aluno/email?email=${encodeURIComponent(this.email)}`, updatedUser)
      .subscribe(
        response => {
          console.log('Dados do aluno atualizados com sucesso', response);
          this.router.navigate(['/profile']); 
        },
        error => {
          console.error('Erro ao atualizar os dados do aluno', error);
          alert('Erro ao atualizar os dados do aluno: ');
        }
      );
  }
}
