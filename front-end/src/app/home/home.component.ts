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
  
  getCompeticoes() {
    this.http.get<any[]>('http://localhost:8080/api/evento')
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
