import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IParamWilaya, ParamWilaya } from 'app/shared/model/param-wilaya.model';
import { ParamWilayaService } from './param-wilaya.service';
import { ParamWilayaComponent } from './param-wilaya.component';
import { ParamWilayaDetailComponent } from './param-wilaya-detail.component';
import { ParamWilayaUpdateComponent } from './param-wilaya-update.component';

@Injectable({ providedIn: 'root' })
export class ParamWilayaResolve implements Resolve<IParamWilaya> {
  constructor(private service: ParamWilayaService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IParamWilaya> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((paramWilaya: HttpResponse<ParamWilaya>) => {
          if (paramWilaya.body) {
            return of(paramWilaya.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ParamWilaya());
  }
}

export const paramWilayaRoute: Routes = [
  {
    path: '',
    component: ParamWilayaComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionStockJhipsterApp.paramWilaya.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ParamWilayaDetailComponent,
    resolve: {
      paramWilaya: ParamWilayaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionStockJhipsterApp.paramWilaya.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ParamWilayaUpdateComponent,
    resolve: {
      paramWilaya: ParamWilayaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionStockJhipsterApp.paramWilaya.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ParamWilayaUpdateComponent,
    resolve: {
      paramWilaya: ParamWilayaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionStockJhipsterApp.paramWilaya.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
