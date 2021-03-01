import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IParamFournisseur, ParamFournisseur } from 'app/shared/model/param-fournisseur.model';
import { ParamFournisseurService } from './param-fournisseur.service';
import { ParamFournisseurComponent } from './param-fournisseur.component';
import { ParamFournisseurDetailComponent } from './param-fournisseur-detail.component';
import { ParamFournisseurUpdateComponent } from './param-fournisseur-update.component';

@Injectable({ providedIn: 'root' })
export class ParamFournisseurResolve implements Resolve<IParamFournisseur> {
  constructor(private service: ParamFournisseurService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IParamFournisseur> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((paramFournisseur: HttpResponse<ParamFournisseur>) => {
          if (paramFournisseur.body) {
            return of(paramFournisseur.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ParamFournisseur());
  }
}

export const paramFournisseurRoute: Routes = [
  {
    path: '',
    component: ParamFournisseurComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionStockJhipsterApp.paramFournisseur.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ParamFournisseurDetailComponent,
    resolve: {
      paramFournisseur: ParamFournisseurResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionStockJhipsterApp.paramFournisseur.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ParamFournisseurUpdateComponent,
    resolve: {
      paramFournisseur: ParamFournisseurResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionStockJhipsterApp.paramFournisseur.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ParamFournisseurUpdateComponent,
    resolve: {
      paramFournisseur: ParamFournisseurResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionStockJhipsterApp.paramFournisseur.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
