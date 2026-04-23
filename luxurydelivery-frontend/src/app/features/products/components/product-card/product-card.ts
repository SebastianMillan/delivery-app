import { CommonModule } from '@angular/common';
import { Component, input } from '@angular/core';
import { CurrencyFormatPipe } from '../../../../shared/pipes/currency-format-pipe';
import { Product } from '../../models/product';

@Component({
  selector: 'app-product-card',
  standalone: true,
  imports: [CommonModule, CurrencyFormatPipe],
  templateUrl: './product-card.html',
  styleUrl: './product-card.css'
})
export class ProductCard {
  product = input.required<Product>();
}
