import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IParamCommune, ParamCommune } from 'app/shared/model/param-commune.model';
import { ParamCommuneService } from './param-commune.service';
import { ParamCommuneComponent } from './param-commune.component';
import { ParamCommuneDetailComponent } from './param-commune-detail.component';
import { ParamCommuneUpdateComponent } from './param-commune-update.component';

@Injectable({ providedIn: 'root' })
export class ParamCommuneResolve implements Resolve<IParamCommune> {
  constructor(private service: ParamCommuneService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IParamCommune> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((paramCommune: HttpResponse<ParamCommune>) => {
          if (paramCommune.body) {
            return of(paramCommune.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ParamCommune());
  }
}

export const paramCommuneRoute: Routes = [
  {
    path: '',
    component: ParamCommuneComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionStockJhipsterApp.paramCommune.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ParamCommuneDetailComponent,
    resolve: {
      paramCommune: ParamCommuneResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionStockJhipsterApp.paramCommune.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ParamCommuneUpdateComponent,
    resolve: {
      paramCommune: ParamCommuneResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionStockJhipsterApp.paramCommune.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ParamCommuneUpdateComponent,
    resolve: {
      paramCommune: ParamCommuneResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionStockJhipsterApp.paramCommune.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
