import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CadastroEventoComponent } from './cadastro-evento.component';

describe('CadastroeventoComponent', () => {
  let component: CadastroEventoComponent;
  let fixture: ComponentFixture<CadastroEventoComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CadastroEventoComponent]
    });
    fixture = TestBed.createComponent(CadastroEventoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
