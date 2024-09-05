import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private API_USER_ME: string = 'http://localhost:8080/api/usuario/me';

  constructor(private http: HttpClient) { }

  login(username: string, password: string) {
    const headers = new HttpHeaders(
      {
        authorization : 'Basic ' + btoa(username + ':' + password)
      }
    );
    return this.http.get<Object>(this.API_USER_ME, {headers: headers});
  }
}


