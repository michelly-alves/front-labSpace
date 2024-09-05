import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  competicoes: any[] = [];
  competicoesFiltradas: any[] = [];

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.getCompeticoes();
  }
  //competições do usuario logado
  getCompeticoes() {
    this.http.get<any[]>('http://localhost:8080/api/competicao/usuario')
      .subscribe(
        response => {
          this.competicoes = response;
          this.competicoesFiltradas = [...this.competicoes];
        },
        error => {
          console.error('Erro ao buscar competições', error);
        }
      );
  }
  //pesquisando as competições
  performSearch(searchTerm: string) {
    if (searchTerm) {
      this.competicoesFiltradas = this.competicoes.filter(competicao =>
        competicao.nome.toLowerCase().includes(searchTerm.toLowerCase())
      );
    } else {
      this.competicoesFiltradas = [...this.competicoes];
    }
  }
}
