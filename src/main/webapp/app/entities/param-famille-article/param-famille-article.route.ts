import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IParamFamilleArticle, ParamFamilleArticle } from 'app/shared/model/param-famille-article.model';
import { ParamFamilleArticleService } from './param-famille-article.service';
import { ParamFamilleArticleComponent } from './param-famille-article.component';
import { ParamFamilleArticleDetailComponent } from './param-famille-article-detail.component';
import { ParamFamilleArticleUpdateComponent } from './param-famille-article-update.component';

@Injectable({ providedIn: 'root' })
export class ParamFamilleArticleResolve implements Resolve<IParamFamilleArticle> {
  constructor(private service: ParamFamilleArticleService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IParamFamilleArticle> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((paramFamilleArticle: HttpResponse<ParamFamilleArticle>) => {
          if (paramFamilleArticle.body) {
            return of(paramFamilleArticle.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ParamFamilleArticle());
  }
}

export const paramFamilleArticleRoute: Routes = [
  {
    path: '',
    component: ParamFamilleArticleComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionStockJhipsterApp.paramFamilleArticle.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ParamFamilleArticleDetailComponent,
    resolve: {
      paramFamilleArticle: ParamFamilleArticleResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionStockJhipsterApp.paramFamilleArticle.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ParamFamilleArticleUpdateComponent,
    resolve: {
      paramFamilleArticle: ParamFamilleArticleResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionStockJhipsterApp.paramFamilleArticle.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ParamFamilleArticleUpdateComponent,
    resolve: {
      paramFamilleArticle: ParamFamilleArticleResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionStockJhipsterApp.paramFamilleArticle.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
