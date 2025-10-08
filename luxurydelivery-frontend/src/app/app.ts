import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { TableModule } from 'primeng/table';

@Component({
  selector: 'app-root',
  imports: [
    RouterOutlet,
    TableModule
  ],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('luxurydelivery-frontend');
}
