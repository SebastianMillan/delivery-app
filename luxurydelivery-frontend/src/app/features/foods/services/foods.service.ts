import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { catchError, map, Observable, throwError } from 'rxjs';
import { API_BASE_URL } from '../../../core/tokens/api-base-url.token';
import { Food, FoodPayload } from '../models/food';

@Injectable({
  providedIn: 'root'
})
export class FoodsService {
  private readonly http = inject(HttpClient);
  private readonly apiBaseUrl = inject(API_BASE_URL);
  private readonly endpoint = `${this.apiBaseUrl}/food`;

  getFoods(): Observable<Food[]> {
    return this.http.get<Food[] | null>(this.endpoint).pipe(
      map((response) => response ?? []),
      catchError((error: Error) => throwError(() => new Error(error.message || 'No se pudieron cargar las comidas.')))
    );
  }

  getFoodById(id: number): Observable<Food> {
    return this.http.get<Food>(`${this.endpoint}/${id}`);
  }

  createFood(payload: FoodPayload): Observable<Food> {
    return this.http.post<Food>(this.endpoint, payload);
  }

  updateFood(id: number, payload: FoodPayload): Observable<Food> {
    return this.http.put<Food>(`${this.endpoint}/${id}`, payload);
  }

  deleteFood(id: number): Observable<void> {
    return this.http.delete<void>(`${this.endpoint}/${id}`);
  }
}
