import { Component, input } from '@angular/core';
import { RouterLink } from '@angular/router';
import { AdminEntitySection } from '../../models/admin-entity';

@Component({
  selector: 'app-entity-section-card',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './entity-section-card.html'
})
export class EntitySectionCard {
  section = input.required<AdminEntitySection>();
}
