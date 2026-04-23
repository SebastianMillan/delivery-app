import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { AdminEntitiesService } from '../../services/admin-entities.service';

@Component({
  selector: 'app-admin-shell',
  standalone: true,
  imports: [CommonModule, RouterOutlet, RouterLink, RouterLinkActive],
  templateUrl: './admin-shell.html'
})
export class AdminShell {
  private readonly adminEntitiesService = inject(AdminEntitiesService);

  readonly entities = this.adminEntitiesService.entities;
}
