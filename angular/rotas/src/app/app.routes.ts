import { Routes } from '@angular/router';
import { TitleComponent } from './pages/index/title/tiltle.component';
import { CardComponent } from './pages/portifolio/card/card.component';

export const routes: Routes = [
  { path: '', component: TitleComponent, pathMatch: 'full' },
  //portfolio
  //portfolio/1
  //portfolio/1/abc
  {
    path: 'portfolio',
    component: CardComponent,
    children: [
      { path: ':id', component: CardComponent },
      { path: ':id/:token', component: CardComponent },
    ],
  },
  { path: '**', redirectTo: '' },
];
