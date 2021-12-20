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

  constructor( private service: ContatoService, private fb: FormBuilder) { }

  ngOnInit(): void {
    this.formulario = this.fb.group({
      nome: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]]
    })
  }

  submit(): void {
    //const nomeValidation = this.formulario.controls.nome.errors.required
    //const emailVlidation = this.formulario.controls.email.errors.email

    console.log(this.formulario.value)
    const isValid = this.formulario.valid
    console.log('is valid', isValid)
   // this.service.save(contato).subscribe( response => {
   //   console.log(response)
   // })
  }

}
