import { CommonModule } from '@angular/common';
import { Component, computed, inject, signal } from '@angular/core';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { finalize } from 'rxjs';
import { EntityKey } from '../../models/admin-entity';
import { AdminEntitiesService } from '../../services/admin-entities.service';

@Component({
  selector: 'app-admin-entity-page',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './admin-entity-page.html'
})
export class AdminEntityPage {
  private readonly route = inject(ActivatedRoute);
  private readonly adminEntitiesService = inject(AdminEntitiesService);

  readonly loading = signal(false);
  readonly records = signal<unknown[]>([]);
  readonly entityKey = signal<EntityKey>('product');
  readonly entity = computed(() => this.adminEntitiesService.getEntityDefinition(this.entityKey()));

  constructor() {
    this.route.paramMap.pipe(takeUntilDestroyed()).subscribe((params) => {
      const key = (params.get('entityKey') ?? 'product') as EntityKey;
      this.entityKey.set(key);
      this.loadRecords();
    });
  }

  loadRecords(): void {
    this.loading.set(true);
    this.adminEntitiesService
      .getEntityCollection(this.entityKey())
      .pipe(finalize(() => this.loading.set(false)))
      .subscribe((records) => this.records.set(records));
  }

  recordKeys(record: unknown): string[] {
    if (!record || typeof record !== 'object') {
      return [];
    }

    return Object.keys(record).slice(0, 5);
  }

  cellValue(record: unknown, key: string): string {
    if (!record || typeof record !== 'object') {
      return '-';
    }

    const value = (record as Record<string, unknown>)[key];
    if (value === null || value === undefined) {
      return '-';
    }

    return typeof value === 'object' ? JSON.stringify(value) : String(value);
  }
}
