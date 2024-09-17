import { NgClass, NgFor, NgIf, NgStyle } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule, NgModel } from '@angular/forms';

@Component({
  selector: 'app-comp-atributos',
  standalone: true,
  imports: [NgClass, NgStyle, NgIf, NgFor, FormsModule],
  templateUrl: './comp-atributos.component.html',
  styleUrl: './comp-atributos.component.css',
})
export class CompAtributosComponent {
  estilo: string = 'disable';
  corFundo: string = 'blue';
  corDafonte: string = 'yellow';
  item: string = '';
  lista: string[] = [];
  isEnableBlock: boolean = false;

  constructor() {}

  adicionarLista() {
    this.lista.push(this.item);
  }

  ngOnInit(): void {}

  trocar() {
    if (this.estilo === 'disable') {
      this.estilo = 'enable';
    } else {
      this.estilo = 'disable';
    }
  }
}
