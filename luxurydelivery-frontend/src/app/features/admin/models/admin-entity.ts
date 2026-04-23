export type EntityKey =
  | 'product'
  | 'category'
  | 'allergen'
  | 'type'
  | 'restaurant'
  | 'food'
  | 'drink';

export interface AdminEntityDefinition {
  key: EntityKey;
  label: string;
  description: string;
  accentClass: string;
}

export interface AdminEntitySection extends AdminEntityDefinition {
  total: number;
  status: 'ok' | 'error';
}
