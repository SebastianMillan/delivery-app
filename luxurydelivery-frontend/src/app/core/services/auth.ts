import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class Auth {
  private readonly tokenStorageKey = 'luxury_delivery_token';

  getToken(): string | null {
    return localStorage.getItem(this.tokenStorageKey);
  }
}
