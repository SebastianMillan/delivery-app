import { inject, Injectable } from '@angular/core';
import { catchError, forkJoin, map, Observable, of } from 'rxjs';
import { AllergensService } from '../../allergens/services/allergens.service';
import { CategoriesService } from '../../categories/services/categories.service';
import { DrinksService } from '../../drinks/services/drinks.service';
import { FoodsService } from '../../foods/services/foods.service';
import { ProductsService } from '../../products/services/products.service';
import { RestaurantsService } from '../../restaurants/services/restaurants.service';
import { TypesService } from '../../types/services/types.service';
import { AdminEntityDefinition, AdminEntitySection, EntityKey } from '../models/admin-entity';

@Injectable({
  providedIn: 'root'
})
export class AdminEntitiesService {
  private readonly productsService = inject(ProductsService);
  private readonly categoriesService = inject(CategoriesService);
  private readonly allergensService = inject(AllergensService);
  private readonly typesService = inject(TypesService);
  private readonly restaurantsService = inject(RestaurantsService);
  private readonly foodsService = inject(FoodsService);
  private readonly drinksService = inject(DrinksService);

  readonly entities: readonly AdminEntityDefinition[] = [
    {
      key: 'product',
      label: 'Productos',
      description: 'Gestión del catálogo principal de productos premium.',
      accentClass: 'from-amber-200/40 to-transparent'
    },
    {
      key: 'category',
      label: 'Categorías',
      description: 'Agrupa productos para mejorar navegación y reporting.',
      accentClass: 'from-violet-200/40 to-transparent'
    },
    {
      key: 'allergen',
      label: 'Alérgenos',
      description: 'Control de información alimentaria y cumplimiento legal.',
      accentClass: 'from-rose-200/40 to-transparent'
    },
    {
      key: 'type',
      label: 'Tipos',
      description: 'Clasificación de entidad para reglas de negocio.',
      accentClass: 'from-emerald-200/40 to-transparent'
    },
    {
      key: 'restaurant',
      label: 'Restaurantes',
      description: 'Administración de partners y cocinas activas.',
      accentClass: 'from-sky-200/40 to-transparent'
    },
    {
      key: 'food',
      label: 'Comidas',
      description: 'Subconjunto de producto para platos elaborados.',
      accentClass: 'from-fuchsia-200/40 to-transparent'
    },
    {
      key: 'drink',
      label: 'Bebidas',
      description: 'Subconjunto de producto para bebidas y maridajes.',
      accentClass: 'from-cyan-200/40 to-transparent'
    }
  ] as const;

  getEntitySections(): Observable<AdminEntitySection[]> {
    return forkJoin(
      this.entities.map((entity) =>
        this.getEntityCollection(entity.key).pipe(
          map((items) => ({ ...entity, total: items.length, status: 'ok' as const })),
          catchError(() => of({ ...entity, total: 0, status: 'error' as const }))
        )
      )
    );
  }

  getEntityCollection(entityKey: EntityKey): Observable<unknown[]> {
    switch (entityKey) {
      case 'product':
        return this.productsService.getProducts().pipe(map((items) => items as unknown[]));
      case 'category':
        return this.categoriesService.getCategories().pipe(map((items) => items as unknown[]));
      case 'allergen':
        return this.allergensService.getAllergens().pipe(map((items) => items as unknown[]));
      case 'type':
        return this.typesService.getTypes().pipe(map((items) => items as unknown[]));
      case 'restaurant':
        return this.restaurantsService.getRestaurants().pipe(map((items) => items as unknown[]));
      case 'food':
        return this.foodsService.getFoods().pipe(map((items) => items as unknown[]));
      case 'drink':
        return this.drinksService.getDrinks().pipe(map((items) => items as unknown[]));
      default:
        return of([]);
    }
  }

  getEntityDefinition(entityKey: EntityKey): AdminEntityDefinition | undefined {
    return this.entities.find((entity) => entity.key === entityKey);
  }
}
