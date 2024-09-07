import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cadastro-evento',
  templateUrl: './cadastro-evento.component.html',
  styleUrls: ['./cadastro-evento.component.css']
})
export class CadastroEventoComponent {
  nome: string = '';
  descricao: string = '';
  horario: string = ''; 
  dia: string = '';
  sala: string = '';   

  constructor(private router: Router, private http: HttpClient) {}

  cadastrarevento() {
    if (!this.nome || !this.descricao || !this.horario || !this.sala || !this.dia) {
      console.error('Por favor, preencha todos os campos obrigatórios.');
      alert('Por favor, preencha todos os campos obrigatórios.');
      return;
    }

    const evento = {
      nome: this.nome,
      descricao: this.descricao,
      horario: this.horario, 
      dia: this.dia,
      sala: this.sala        
    };

    this.http.post('http://localhost:8080/api/evento', evento)
      .subscribe(
        (response: any) => {
          console.log('Evento cadastrado com sucesso', response);
          const idCompeticao = response.id;
          this.router.navigate(['/home', idCompeticao]);
        },
        error => {
          console.error('Erro ao cadastrar evento', error);
        }
      );
  }

  cancelar(){
    this.router.navigate(['/home']);
  };
}
