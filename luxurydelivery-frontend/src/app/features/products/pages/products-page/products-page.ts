import { CommonModule } from '@angular/common';
import { Component, computed, inject, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { finalize } from 'rxjs';
import { Button } from '../../../../shared/components/button/button';
import { Autofocus } from '../../../../shared/directives/autofocus';
import { Product } from '../../models/product';
import { ProductsService } from '../../services/products.service';
import { ProductCard } from '../../components/product-card/product-card';

@Component({
  selector: 'app-products-page',
  standalone: true,
  imports: [CommonModule, FormsModule, ProductCard, Button, Autofocus],
  templateUrl: './products-page.html',
  styleUrl: './products-page.css'
})
export class ProductsPage {
  private readonly productsService = inject(ProductsService);

  readonly searchTerm = signal('');
  readonly loading = signal(false);
  readonly errorMessage = signal('');
  readonly products = signal<Product[]>([]);

  readonly filteredProducts = computed(() => {
    const term = this.searchTerm().trim().toLowerCase();
    if (!term) {
      return this.products();
    }

    return this.products().filter((product) =>
      `${product.name} ${product.category}`.toLowerCase().includes(term)
    );
  });

  constructor() {
    this.loadProducts();
  }

  loadProducts(): void {
    this.loading.set(true);
    this.errorMessage.set('');

    this.productsService
      .getProducts()
      .pipe(finalize(() => this.loading.set(false)))
      .subscribe({
        next: (products) => this.products.set(products),
        error: (error: Error) => this.errorMessage.set(error.message || 'Error inesperado cargando productos.')
      });
  }
}
