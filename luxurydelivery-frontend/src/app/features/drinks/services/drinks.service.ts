import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { catchError, map, Observable, throwError } from 'rxjs';
import { API_BASE_URL } from '../../../core/tokens/api-base-url.token';
import { Drink, DrinkPayload } from '../models/drink';

@Injectable({
  providedIn: 'root'
})
export class DrinksService {
  private readonly http = inject(HttpClient);
  private readonly apiBaseUrl = inject(API_BASE_URL);
  private readonly endpoint = `${this.apiBaseUrl}/drink`;

  getDrinks(): Observable<Drink[]> {
    return this.http.get<Drink[] | null>(this.endpoint).pipe(
      map((response) => response ?? []),
      catchError((error: Error) => throwError(() => new Error(error.message || 'No se pudieron cargar las bebidas.')))
    );
  }

  getDrinkById(id: number): Observable<Drink> {
    return this.http.get<Drink>(`${this.endpoint}/${id}`);
  }

  createDrink(payload: DrinkPayload): Observable<Drink> {
    return this.http.post<Drink>(this.endpoint, payload);
  }

  updateDrink(id: number, payload: DrinkPayload): Observable<Drink> {
    return this.http.put<Drink>(`${this.endpoint}/${id}`, payload);
  }

  deleteDrink(id: number): Observable<void> {
    return this.http.delete<void>(`${this.endpoint}/${id}`);
  }
}
