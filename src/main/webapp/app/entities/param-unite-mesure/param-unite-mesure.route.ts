import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IParamUniteMesure, ParamUniteMesure } from 'app/shared/model/param-unite-mesure.model';
import { ParamUniteMesureService } from './param-unite-mesure.service';
import { ParamUniteMesureComponent } from './param-unite-mesure.component';
import { ParamUniteMesureDetailComponent } from './param-unite-mesure-detail.component';
import { ParamUniteMesureUpdateComponent } from './param-unite-mesure-update.component';

@Injectable({ providedIn: 'root' })
export class ParamUniteMesureResolve implements Resolve<IParamUniteMesure> {
  constructor(private service: ParamUniteMesureService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IParamUniteMesure> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((paramUniteMesure: HttpResponse<ParamUniteMesure>) => {
          if (paramUniteMesure.body) {
            return of(paramUniteMesure.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ParamUniteMesure());
  }
}

export const paramUniteMesureRoute: Routes = [
  {
    path: '',
    component: ParamUniteMesureComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionStockJhipsterApp.paramUniteMesure.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ParamUniteMesureDetailComponent,
    resolve: {
      paramUniteMesure: ParamUniteMesureResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionStockJhipsterApp.paramUniteMesure.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ParamUniteMesureUpdateComponent,
    resolve: {
      paramUniteMesure: ParamUniteMesureResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionStockJhipsterApp.paramUniteMesure.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ParamUniteMesureUpdateComponent,
    resolve: {
      paramUniteMesure: ParamUniteMesureResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionStockJhipsterApp.paramUniteMesure.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
