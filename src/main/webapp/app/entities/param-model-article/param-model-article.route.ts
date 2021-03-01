import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IParamModelArticle, ParamModelArticle } from 'app/shared/model/param-model-article.model';
import { ParamModelArticleService } from './param-model-article.service';
import { ParamModelArticleComponent } from './param-model-article.component';
import { ParamModelArticleDetailComponent } from './param-model-article-detail.component';
import { ParamModelArticleUpdateComponent } from './param-model-article-update.component';

@Injectable({ providedIn: 'root' })
export class ParamModelArticleResolve implements Resolve<IParamModelArticle> {
  constructor(private service: ParamModelArticleService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IParamModelArticle> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((paramModelArticle: HttpResponse<ParamModelArticle>) => {
          if (paramModelArticle.body) {
            return of(paramModelArticle.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ParamModelArticle());
  }
}

export const paramModelArticleRoute: Routes = [
  {
    path: '',
    component: ParamModelArticleComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionStockJhipsterApp.paramModelArticle.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ParamModelArticleDetailComponent,
    resolve: {
      paramModelArticle: ParamModelArticleResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionStockJhipsterApp.paramModelArticle.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ParamModelArticleUpdateComponent,
    resolve: {
      paramModelArticle: ParamModelArticleResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionStockJhipsterApp.paramModelArticle.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ParamModelArticleUpdateComponent,
    resolve: {
      paramModelArticle: ParamModelArticleResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gestionStockJhipsterApp.paramModelArticle.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
