import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { catchError, forkJoin, map, Observable, of } from 'rxjs';
import { API_BASE_URL } from '../../../core/tokens/api-base-url.token';
import { AdminEntityDefinition, AdminEntitySection, EntityKey } from '../models/admin-entity';

@Injectable({
  providedIn: 'root'
})
export class AdminEntitiesService {
  private readonly http = inject(HttpClient);
  private readonly apiBaseUrl = inject(API_BASE_URL);

  readonly entities: readonly AdminEntityDefinition[] = [
    {
      key: 'product',
      label: 'Productos',
      description: 'Gestión del catálogo principal de productos premium.',
      endpoint: 'product',
      accentClass: 'from-amber-200/40 to-transparent'
    },
    {
      key: 'category',
      label: 'Categorías',
      description: 'Agrupa productos para mejorar navegación y reporting.',
      endpoint: 'category',
      accentClass: 'from-violet-200/40 to-transparent'
    },
    {
      key: 'allergen',
      label: 'Alérgenos',
      description: 'Control de información alimentaria y cumplimiento legal.',
      endpoint: 'allergen',
      accentClass: 'from-rose-200/40 to-transparent'
    },
    {
      key: 'type',
      label: 'Tipos',
      description: 'Clasificación de entidad para reglas de negocio.',
      endpoint: 'type',
      accentClass: 'from-emerald-200/40 to-transparent'
    },
    {
      key: 'restaurant',
      label: 'Restaurantes',
      description: 'Administración de partners y cocinas activas.',
      endpoint: 'restaurant',
      accentClass: 'from-sky-200/40 to-transparent'
    },
    {
      key: 'food',
      label: 'Comidas',
      description: 'Subconjunto de producto para platos elaborados.',
      endpoint: 'food',
      accentClass: 'from-fuchsia-200/40 to-transparent'
    },
    {
      key: 'drink',
      label: 'Bebidas',
      description: 'Subconjunto de producto para bebidas y maridajes.',
      endpoint: 'drink',
      accentClass: 'from-cyan-200/40 to-transparent'
    }
  ] as const;

  getEntitySections(): Observable<AdminEntitySection[]> {
    const requests = this.entities.map((entity) =>
      this.http.get<unknown[] | null>(`${this.apiBaseUrl}/${entity.endpoint}`).pipe(
        map((items) => ({ ...entity, total: items?.length ?? 0, status: 'ok' as const })),
        catchError(() => of({ ...entity, total: 0, status: 'error' as const }))
      )
    );

    return forkJoin(requests);
  }

  getEntityCollection(entityKey: EntityKey): Observable<unknown[]> {
    const entity = this.getEntityDefinition(entityKey);
    if (!entity) {
      return of([]);
    }

    return this.http.get<unknown[] | null>(`${this.apiBaseUrl}/${entity.endpoint}`).pipe(
      map((response) => response ?? []),
      catchError(() => of([]))
    );
  }

  getEntityDefinition(entityKey: EntityKey): AdminEntityDefinition | undefined {
    return this.entities.find((entity) => entity.key === entityKey);
  }
}
