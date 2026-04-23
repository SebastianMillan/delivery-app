export interface Allergen {
  id: number;
  name: string;
}

export type AllergenPayload = Omit<Allergen, 'id'>;
