import { CommonModule } from '@angular/common';
import { Component, computed, inject, signal } from '@angular/core';
import { finalize } from 'rxjs';
import { EntitySectionCard } from '../../components/entity-section-card/entity-section-card';
import { AdminEntitiesService } from '../../services/admin-entities.service';
import { AdminEntitySection } from '../../models/admin-entity';

@Component({
  selector: 'app-admin-dashboard-page',
  standalone: true,
  imports: [CommonModule, EntitySectionCard],
  templateUrl: './admin-dashboard-page.html'
})
export class AdminDashboardPage {
  private readonly adminEntitiesService = inject(AdminEntitiesService);

  readonly loading = signal(false);
  readonly sections = signal<AdminEntitySection[]>([]);
  readonly onlineSections = computed(() => this.sections().filter((section) => section.status === 'ok').length);
  readonly totalRecords = computed(() => this.sections().reduce((sum, section) => sum + section.total, 0));

  constructor() {
    this.loadSections();
  }

  loadSections(): void {
    this.loading.set(true);
    this.adminEntitiesService
      .getEntitySections()
      .pipe(finalize(() => this.loading.set(false)))
      .subscribe((sections) => this.sections.set(sections));
  }
}
