import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { CardComponent } from './card/card.component';
import { NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { CompAtributosComponent } from './comp-atributos/comp-atributos.component';
import { SharedModule } from './shared/shared.module';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    RouterOutlet,
    CardComponent,
    NgIf,
    CompAtributosComponent,
    SharedModule,
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent {
  title = 'diretivas-proj';
  isAliveCard = true;
}
