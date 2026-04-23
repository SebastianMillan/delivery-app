export interface Food {
  id: number;
  name: string;
  price?: number;
}

export type FoodPayload = Omit<Food, 'id'>;
