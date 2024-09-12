import {
  NgFor,
  NgSwitch,
  NgSwitchCase,
  NgSwitchDefault,
} from '@angular/common';
import { Component } from '@angular/core';

@Component({
  selector: 'app-card',
  standalone: true,
  imports: [NgFor, NgSwitch, NgSwitchDefault, NgSwitchCase],
  templateUrl: './card.component.html',
  styleUrl: './card.component.css',
})
export class CardComponent {
  produtos: string[] = [];
  menuType: string = 'diretor';

  constructor() {
    this.produtos = ['mouse', 'teclado', 'cabo', 'font'];
  }

  ngOnInit(): void {}

  adicionar() {
    this.produtos.push('produto');
  }

  remover(index: number) {
    this.produtos.splice(index, 1);
  }
}
