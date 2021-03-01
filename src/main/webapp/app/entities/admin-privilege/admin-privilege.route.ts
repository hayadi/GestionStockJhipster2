import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IAdminPrivilege, AdminPrivilege } from 'app/shared/model/admin-privilege.model';
import { AdminPrivilegeService } from './admin-privilege.service';
import { AdminPrivilegeComponent } from './admin-privilege.component';
import { AdminPrivilegeDetailComponent } from './admin-privilege-detail.component';
import { AdminPrivilegeUpdateComponent } from './admin-privilege-update.component';

@Injectable({ providedIn: 'root' })
export class AdminPrivilegeResolve implements Resolve<IAdminPrivilege> {
  constructor(private service: AdminPrivilegeService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IAdminPrivilege> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((adminPrivilege: HttpResponse<AdminPrivilege>) => {
          if (adminPrivilege.body) {
            return of(adminPrivilege.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new AdminPrivilege());
  }
}

export const adminPrivilegeRoute: Routes = [
  {
    path: '',
    component: AdminPrivilegeComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionStockJhipsterApp.adminPrivilege.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AdminPrivilegeDetailComponent,
    resolve: {
      adminPrivilege: AdminPrivilegeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionStockJhipsterApp.adminPrivilege.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: AdminPrivilegeUpdateComponent,
    resolve: {
      adminPrivilege: AdminPrivilegeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionStockJhipsterApp.adminPrivilege.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: AdminPrivilegeUpdateComponent,
    resolve: {
      adminPrivilege: AdminPrivilegeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionStockJhipsterApp.adminPrivilege.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
