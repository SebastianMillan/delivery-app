import { Component, computed, input, output } from '@angular/core';

@Component({
  selector: 'app-button',
  standalone: true,
  imports: [],
  templateUrl: './button.html',
  styleUrl: './button.css'
})
export class Button {
  label = input('Botón');
  variant = input<'primary' | 'secondary'>('primary');
  disabled = input(false);
  clicked = output<void>();

  buttonClass = computed(() => {
    const base = 'rounded-md px-4 py-2 text-sm font-medium transition focus:outline-none focus:ring-2 focus:ring-offset-1';
    return this.variant() === 'secondary'
      ? `${base} border border-slate-300 text-slate-700 hover:bg-slate-50 focus:ring-slate-400`
      : `${base} bg-emerald-600 text-white hover:bg-emerald-700 focus:ring-emerald-500`;
  });

  onClick(): void {
    if (!this.disabled()) {
      this.clicked.emit();
    }
  }
}
