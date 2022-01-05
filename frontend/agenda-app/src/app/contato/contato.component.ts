import { ContatoDetalheComponent } from './../contato-detalhe/contato-detalhe.component';
import { MatDialog } from '@angular/material/dialog';
import { Component, OnInit } from '@angular/core';
import { ContatoService } from '../contato.service';
import { Contato } from './contato';
import { FormBuilder, Validators } from '@angular/forms';
import { FormGroup } from '@angular/forms';
import { PageEvent } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-contato',
  templateUrl: './contato.component.html',
  styleUrls: ['./contato.component.css']
})
export class ContatoComponent implements OnInit {

  formulario!: FormGroup
  contatos: Contato[] = []
  colunas: string[] = ['foto', 'id', 'nome', 'email', 'favorito']
  totalElementos = 0
  pagina = 0
  tamanhoPagina = 5
  pageSizeOptions: number[] = [5]
  snackDurationInSeconds = 5

  constructor( private service: ContatoService,
    private fb: FormBuilder,
    private dialog: MatDialog,
    private snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.montarFormulario()
    this.listarContatos(this.pagina, this.tamanhoPagina)
  }

  montarFormulario() {
    this.formulario = this.fb.group({
      nome: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]]
    })
  }

  listarContatos(pagina = 0, tamanho = 5) {
    this.service.listAll(pagina, tamanho).subscribe( response => {
      this.contatos = response.content
      this.totalElementos = response.totalElements
      this.pagina = response.pageNumber
    })
  }

  favoritar(contato: Contato) {
    this.service.favorite(contato).subscribe( response => {
      contato.favorito = !contato.favorito
    })
  }

  submit(): void {
    const formValues = this.formulario.value
    const contato: Contato = new Contato(formValues.nome, formValues.email)
    this.service.save(contato).subscribe( response => {
      this.listarContatos()
      this.snackBar.open("Cadastrado com sucesso", "Fechar",  {
        duration: this.snackDurationInSeconds * 1000
      })
      this.formulario.reset()
    })
  }

  uploadFoto(event: any, contato: Contato) {
    const files = event.target.files
    if (files) {
      const foto = files[0]
      const formData: FormData = new FormData()
      formData.append("foto", foto)
      this.service
        .upload(contato, formData)
        .subscribe( response => this.listarContatos())
    }
  }

  visualizarContato(contato: Contato) {
    this.dialog.open(ContatoDetalheComponent, {
      width: '400px',
      height: '450px',
      data: contato
    })
  }

  paginar(event: PageEvent) {
    this.pagina = event.pageIndex
    this.listarContatos(this.pagina, this.tamanhoPagina)
  }

}


