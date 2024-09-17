import {
  Component,
  Input,
  OnChanges,
  OnInit,
  SimpleChanges,
} from '@angular/core';

@Component({
  selector: 'app-title',
  standalone: true,
  imports: [],
  templateUrl: './title.component.html',
  styleUrl: './title.component.css',
})
export class TitleComponent implements OnInit, OnChanges {
  @Input() name: string = '';

  constructor() {
    console.log(`constructor ${this.name}`);
  }

  ngOnInit(): void {
    console.log(`ngOnInit ${this.name}`);
  }

  ngOnChanges(changes: SimpleChanges): void {
    console.log(`ngOnChanges ${this.name}`);
  }
}
