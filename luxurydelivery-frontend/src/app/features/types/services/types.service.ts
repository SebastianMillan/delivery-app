import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { catchError, map, Observable, throwError } from 'rxjs';
import { API_BASE_URL } from '../../../core/tokens/api-base-url.token';
import { ProductType, ProductTypePayload } from '../models/type';

@Injectable({
  providedIn: 'root'
})
export class TypesService {
  private readonly http = inject(HttpClient);
  private readonly apiBaseUrl = inject(API_BASE_URL);
  private readonly endpoint = `${this.apiBaseUrl}/type`;

  getTypes(): Observable<ProductType[]> {
    return this.http.get<ProductType[] | null>(this.endpoint).pipe(
      map((response) => response ?? []),
      catchError((error: Error) => throwError(() => new Error(error.message || 'No se pudieron cargar los tipos.')))
    );
  }

  getTypeById(id: number): Observable<ProductType> {
    return this.http.get<ProductType>(`${this.endpoint}/${id}`);
  }

  createType(payload: ProductTypePayload): Observable<ProductType> {
    return this.http.post<ProductType>(this.endpoint, payload);
  }

  updateType(id: number, payload: ProductTypePayload): Observable<ProductType> {
    return this.http.put<ProductType>(`${this.endpoint}/${id}`, payload);
  }

  deleteType(id: number): Observable<void> {
    return this.http.delete<void>(`${this.endpoint}/${id}`);
  }
}
