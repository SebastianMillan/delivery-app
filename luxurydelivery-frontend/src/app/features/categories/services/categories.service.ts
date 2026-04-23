import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { catchError, map, Observable, throwError } from 'rxjs';
import { API_BASE_URL } from '../../../core/tokens/api-base-url.token';
import { Category, CategoryPayload } from '../models/category';

@Injectable({
  providedIn: 'root'
})
export class CategoriesService {
  private readonly http = inject(HttpClient);
  private readonly apiBaseUrl = inject(API_BASE_URL);
  private readonly endpoint = `${this.apiBaseUrl}/category`;

  getCategories(): Observable<Category[]> {
    return this.http.get<Category[] | null>(this.endpoint).pipe(
      map((response) => response ?? []),
      catchError((error: Error) => throwError(() => new Error(error.message || 'No se pudieron cargar las categorías.')))
    );
  }

  getCategoryById(id: number): Observable<Category> {
    return this.http.get<Category>(`${this.endpoint}/${id}`);
  }

  createCategory(payload: CategoryPayload): Observable<Category> {
    return this.http.post<Category>(this.endpoint, payload);
  }

  updateCategory(id: number, payload: CategoryPayload): Observable<Category> {
    return this.http.put<Category>(`${this.endpoint}/${id}`, payload);
  }

  deleteCategory(id: number): Observable<void> {
    return this.http.delete<void>(`${this.endpoint}/${id}`);
  }
}
