export interface ProductAllergen {
  id: number;
  name: string;
}

export interface Product {
  id: number;
  name: string;
  image: string;
  price: number;
  category: string;
  allergenList: ProductAllergen[];
}

export type ProductPayload = Omit<Product, 'id'>;
