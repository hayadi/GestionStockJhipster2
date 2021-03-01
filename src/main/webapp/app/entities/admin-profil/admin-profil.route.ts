import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IAdminProfil, AdminProfil } from 'app/shared/model/admin-profil.model';
import { AdminProfilService } from './admin-profil.service';
import { AdminProfilComponent } from './admin-profil.component';
import { AdminProfilDetailComponent } from './admin-profil-detail.component';
import { AdminProfilUpdateComponent } from './admin-profil-update.component';

@Injectable({ providedIn: 'root' })
export class AdminProfilResolve implements Resolve<IAdminProfil> {
  constructor(private service: AdminProfilService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IAdminProfil> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((adminProfil: HttpResponse<AdminProfil>) => {
          if (adminProfil.body) {
            return of(adminProfil.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new AdminProfil());
  }
}

export const adminProfilRoute: Routes = [
  {
    path: '',
    component: AdminProfilComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionStockJhipsterApp.adminProfil.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AdminProfilDetailComponent,
    resolve: {
      adminProfil: AdminProfilResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionStockJhipsterApp.adminProfil.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: AdminProfilUpdateComponent,
    resolve: {
      adminProfil: AdminProfilResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionStockJhipsterApp.adminProfil.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: AdminProfilUpdateComponent,
    resolve: {
      adminProfil: AdminProfilResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionStockJhipsterApp.adminProfil.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
