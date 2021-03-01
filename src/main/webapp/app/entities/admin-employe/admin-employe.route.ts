import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IAdminEmploye, AdminEmploye } from 'app/shared/model/admin-employe.model';
import { AdminEmployeService } from './admin-employe.service';
import { AdminEmployeComponent } from './admin-employe.component';
import { AdminEmployeDetailComponent } from './admin-employe-detail.component';
import { AdminEmployeUpdateComponent } from './admin-employe-update.component';

@Injectable({ providedIn: 'root' })
export class AdminEmployeResolve implements Resolve<IAdminEmploye> {
  constructor(private service: AdminEmployeService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IAdminEmploye> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((adminEmploye: HttpResponse<AdminEmploye>) => {
          if (adminEmploye.body) {
            return of(adminEmploye.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new AdminEmploye());
  }
}

export const adminEmployeRoute: Routes = [
  {
    path: '',
    component: AdminEmployeComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionStockJhipsterApp.adminEmploye.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AdminEmployeDetailComponent,
    resolve: {
      adminEmploye: AdminEmployeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionStockJhipsterApp.adminEmploye.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: AdminEmployeUpdateComponent,
    resolve: {
      adminEmploye: AdminEmployeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionStockJhipsterApp.adminEmploye.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: AdminEmployeUpdateComponent,
    resolve: {
      adminEmploye: AdminEmployeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionStockJhipsterApp.adminEmploye.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
