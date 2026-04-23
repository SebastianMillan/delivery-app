import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { catchError, map, Observable, throwError } from 'rxjs';
import { API_BASE_URL } from '../../../core/tokens/api-base-url.token';
import { Restaurant, RestaurantPayload } from '../models/restaurant';

@Injectable({
  providedIn: 'root'
})
export class RestaurantsService {
  private readonly http = inject(HttpClient);
  private readonly apiBaseUrl = inject(API_BASE_URL);
  private readonly endpoint = `${this.apiBaseUrl}/restaurant`;

  getRestaurants(): Observable<Restaurant[]> {
    return this.http.get<Restaurant[] | null>(this.endpoint).pipe(
      map((response) => response ?? []),
      catchError((error: Error) => throwError(() => new Error(error.message || 'No se pudieron cargar los restaurantes.')))
    );
  }

  getRestaurantById(id: number): Observable<Restaurant> {
    return this.http.get<Restaurant>(`${this.endpoint}/${id}`);
  }

  createRestaurant(payload: RestaurantPayload): Observable<Restaurant> {
    return this.http.post<Restaurant>(this.endpoint, payload);
  }

  updateRestaurant(id: number, payload: RestaurantPayload): Observable<Restaurant> {
    return this.http.put<Restaurant>(`${this.endpoint}/${id}`, payload);
  }

  deleteRestaurant(id: number): Observable<void> {
    return this.http.delete<void>(`${this.endpoint}/${id}`);
  }
}
