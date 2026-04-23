import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { catchError, map, Observable, throwError } from 'rxjs';
import { API_BASE_URL } from '../../../core/tokens/api-base-url.token';
import { Product, ProductPayload } from '../models/product';

@Injectable({
  providedIn: 'root'
})
export class ProductsService {
  private readonly http = inject(HttpClient);
  private readonly apiBaseUrl = inject(API_BASE_URL);
  private readonly endpoint = `${this.apiBaseUrl}/product`;

  getProducts(): Observable<Product[]> {
    return this.http.get<Product[] | null>(this.endpoint).pipe(
      map((response) => response ?? []),
      catchError((error: Error) => throwError(() => new Error(error.message || 'No se pudieron cargar los productos.')))
    );
  }

  getProductById(id: number): Observable<Product> {
    return this.http.get<Product>(`${this.endpoint}/${id}`);
  }

  createProduct(payload: ProductPayload): Observable<Product> {
    return this.http.post<Product>(this.endpoint, payload);
  }

  updateProduct(id: number, payload: ProductPayload): Observable<Product> {
    return this.http.put<Product>(`${this.endpoint}/${id}`, payload);
  }

  deleteProduct(id: number): Observable<void> {
    return this.http.delete<void>(`${this.endpoint}/${id}`);
  }
}
