export interface ProductType {
  id: number;
  name: string;
}

export type ProductTypePayload = Omit<ProductType, 'id'>;
