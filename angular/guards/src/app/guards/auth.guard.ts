import { CanActivateFn } from '@angular/router';

export const authGuard: CanActivateFn = (_route, _state) => {
  const hasToken = !!localStorage.getItem('token');

  return hasToken;
};
