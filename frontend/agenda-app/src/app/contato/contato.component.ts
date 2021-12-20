import { Component, OnInit } from '@angular/core';
import { ContatoService } from '../contato.service';
import { Contato } from './contato';
import { FormBuilder, Validators } from '@angular/forms';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-contato',
  templateUrl: './contato.component.html',
  styleUrls: ['./contato.component.css']
})
export class ContatoComponent implements OnInit {

  formulario!: FormGroup
  contatos: Contato[] = []

  constructor( private service: ContatoService, private fb: FormBuilder) { }

  ngOnInit(): void {
    this.formulario = this.fb.group({
      nome: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]]
    })
  }

  submit(): void {
    const formValues = this.formulario.value
    const contato: Contato = new Contato(formValues.nome, formValues.email)
    this.service.save(contato).subscribe( response => {
      this.contatos.push(response)
      console.log(this.contatos)
    })
  }

}
