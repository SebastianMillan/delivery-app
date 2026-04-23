export interface Drink {
  id: number;
  name: string;
  price?: number;
}

export type DrinkPayload = Omit<Drink, 'id'>;
