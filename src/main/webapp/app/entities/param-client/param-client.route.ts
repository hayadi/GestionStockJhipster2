import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IParamClient, ParamClient } from 'app/shared/model/param-client.model';
import { ParamClientService } from './param-client.service';
import { ParamClientComponent } from './param-client.component';
import { ParamClientDetailComponent } from './param-client-detail.component';
import { ParamClientUpdateComponent } from './param-client-update.component';

@Injectable({ providedIn: 'root' })
export class ParamClientResolve implements Resolve<IParamClient> {
  constructor(private service: ParamClientService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IParamClient> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((paramClient: HttpResponse<ParamClient>) => {
          if (paramClient.body) {
            return of(paramClient.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ParamClient());
  }
}

export const paramClientRoute: Routes = [
  {
    path: '',
    component: ParamClientComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionStockJhipsterApp.paramClient.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ParamClientDetailComponent,
    resolve: {
      paramClient: ParamClientResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionStockJhipsterApp.paramClient.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ParamClientUpdateComponent,
    resolve: {
      paramClient: ParamClientResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionStockJhipsterApp.paramClient.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ParamClientUpdateComponent,
    resolve: {
      paramClient: ParamClientResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionStockJhipsterApp.paramClient.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
