import { HttpErrorResponse, HttpInterceptorFn } from '@angular/common/http';
import { catchError, throwError } from 'rxjs';

export const errorInterceptor: HttpInterceptorFn = (req, next) => {
  return next(req).pipe(
    catchError((error: unknown) => {
      if (error instanceof HttpErrorResponse) {
        if (error.status === 0) {
          return throwError(() => new Error('No se pudo conectar con el backend.'));
        }

        const message = (typeof error.error === 'string' && error.error) || error.message;
        return throwError(() => new Error(message || 'Error inesperado del servidor.'));
      }

      return throwError(() => new Error('Error inesperado.'));
    })
  );
};
