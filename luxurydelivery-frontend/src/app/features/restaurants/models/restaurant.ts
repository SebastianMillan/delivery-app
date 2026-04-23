export interface Restaurant {
  id: number;
  name: string;
  address?: string;
  phone?: string;
}

export type RestaurantPayload = Omit<Restaurant, 'id'>;
