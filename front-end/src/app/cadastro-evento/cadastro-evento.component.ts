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
  quantalunos: string = '';
  premiacao: string = '';
  formaCompeticao: string = '';
  horario: string = ''; // Adicionei o campo de horário
  sala: string = '';    // Adicionei o campo de sala

  constructor(private router: Router, private http: HttpClient) {}

  cadastrarevento() {
    if (!this.nome || !this.descricao || !this.quantalunos || !this.formaCompeticao || !this.horario || !this.sala) {
      console.error('Por favor, preencha todos os campos obrigatórios.');
      alert('Por favor, preencha todos os campos obrigatórios.');
      return;
    }

    const evento = {
      nome: this.nome,
      descricao: this.descricao,
      quantalunos: this.quantalunos,
      premiacao: this.premiacao,
      formaCompeticao: this.formaCompeticao,
      horario: this.horario, // Adicionei o campo de horário
      sala: this.sala        // Adicionei o campo de sala
    };

    this.http.post('http://localhost:8080/api/competicao', evento)
      .subscribe(
        (response: any) => {
          console.log('Evento cadastrado com sucesso', response);
          const idCompeticao = response.id;
          this.router.navigate(['/inserir-time', idCompeticao]);
        },
        error => {
          console.error('Erro ao cadastrar evento', error);
        }
      );
  }
}
