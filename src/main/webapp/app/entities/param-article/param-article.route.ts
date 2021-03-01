import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IParamArticle, ParamArticle } from 'app/shared/model/param-article.model';
import { ParamArticleService } from './param-article.service';
import { ParamArticleComponent } from './param-article.component';
import { ParamArticleDetailComponent } from './param-article-detail.component';
import { ParamArticleUpdateComponent } from './param-article-update.component';

@Injectable({ providedIn: 'root' })
export class ParamArticleResolve implements Resolve<IParamArticle> {
  constructor(private service: ParamArticleService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IParamArticle> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((paramArticle: HttpResponse<ParamArticle>) => {
          if (paramArticle.body) {
            return of(paramArticle.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ParamArticle());
  }
}

export const paramArticleRoute: Routes = [
  {
    path: '',
    component: ParamArticleComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionStockJhipsterApp.paramArticle.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ParamArticleDetailComponent,
    resolve: {
      paramArticle: ParamArticleResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionStockJhipsterApp.paramArticle.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ParamArticleUpdateComponent,
    resolve: {
      paramArticle: ParamArticleResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionStockJhipsterApp.paramArticle.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ParamArticleUpdateComponent,
    resolve: {
      paramArticle: ParamArticleResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionStockJhipsterApp.paramArticle.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
