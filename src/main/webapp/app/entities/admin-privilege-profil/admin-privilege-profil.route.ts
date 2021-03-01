import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IAdminPrivilegeProfil, AdminPrivilegeProfil } from 'app/shared/model/admin-privilege-profil.model';
import { AdminPrivilegeProfilService } from './admin-privilege-profil.service';
import { AdminPrivilegeProfilComponent } from './admin-privilege-profil.component';
import { AdminPrivilegeProfilDetailComponent } from './admin-privilege-profil-detail.component';
import { AdminPrivilegeProfilUpdateComponent } from './admin-privilege-profil-update.component';

@Injectable({ providedIn: 'root' })
export class AdminPrivilegeProfilResolve implements Resolve<IAdminPrivilegeProfil> {
  constructor(private service: AdminPrivilegeProfilService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IAdminPrivilegeProfil> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((adminPrivilegeProfil: HttpResponse<AdminPrivilegeProfil>) => {
          if (adminPrivilegeProfil.body) {
            return of(adminPrivilegeProfil.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new AdminPrivilegeProfil());
  }
}

export const adminPrivilegeProfilRoute: Routes = [
  {
    path: '',
    component: AdminPrivilegeProfilComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionStockJhipsterApp.adminPrivilegeProfil.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AdminPrivilegeProfilDetailComponent,
    resolve: {
      adminPrivilegeProfil: AdminPrivilegeProfilResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionStockJhipsterApp.adminPrivilegeProfil.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: AdminPrivilegeProfilUpdateComponent,
    resolve: {
      adminPrivilegeProfil: AdminPrivilegeProfilResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionStockJhipsterApp.adminPrivilegeProfil.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: AdminPrivilegeProfilUpdateComponent,
    resolve: {
      adminPrivilegeProfil: AdminPrivilegeProfilResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionStockJhipsterApp.adminPrivilegeProfil.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
