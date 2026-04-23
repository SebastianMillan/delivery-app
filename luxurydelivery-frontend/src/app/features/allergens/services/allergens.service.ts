import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { catchError, map, Observable, throwError } from 'rxjs';
import { API_BASE_URL } from '../../../core/tokens/api-base-url.token';
import { Allergen, AllergenPayload } from '../models/allergen';

@Injectable({
  providedIn: 'root'
})
export class AllergensService {
  private readonly http = inject(HttpClient);
  private readonly apiBaseUrl = inject(API_BASE_URL);
  private readonly endpoint = `${this.apiBaseUrl}/allergen`;

  getAllergens(): Observable<Allergen[]> {
    return this.http.get<Allergen[] | null>(this.endpoint).pipe(
      map((response) => response ?? []),
      catchError((error: Error) => throwError(() => new Error(error.message || 'No se pudieron cargar los alérgenos.')))
    );
  }

  getAllergenById(id: number): Observable<Allergen> {
    return this.http.get<Allergen>(`${this.endpoint}/${id}`);
  }

  createAllergen(payload: AllergenPayload): Observable<Allergen> {
    return this.http.post<Allergen>(this.endpoint, payload);
  }

  updateAllergen(id: number, payload: AllergenPayload): Observable<Allergen> {
    return this.http.put<Allergen>(`${this.endpoint}/${id}`, payload);
  }

  deleteAllergen(id: number): Observable<void> {
    return this.http.delete<void>(`${this.endpoint}/${id}`);
  }
}
