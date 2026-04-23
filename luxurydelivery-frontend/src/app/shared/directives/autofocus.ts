import { AfterViewInit, Directive, ElementRef, inject } from '@angular/core';

@Directive({
  selector: '[appAutofocus]',
  standalone: true,
})
export class Autofocus implements AfterViewInit {
  private readonly host = inject(ElementRef<HTMLInputElement>);

  ngAfterViewInit(): void {
    queueMicrotask(() => this.host.nativeElement.focus());
  }
}
