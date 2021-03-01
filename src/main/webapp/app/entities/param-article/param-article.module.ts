import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GestionStockJhipsterSharedModule } from 'app/shared/shared.module';
import { ParamArticleComponent } from './param-article.component';
import { ParamArticleDetailComponent } from './param-article-detail.component';
import { ParamArticleUpdateComponent } from './param-article-update.component';
import { ParamArticleDeleteDialogComponent } from './param-article-delete-dialog.component';
import { paramArticleRoute } from './param-article.route';

@NgModule({
  imports: [GestionStockJhipsterSharedModule, RouterModule.forChild(paramArticleRoute)],
  declarations: [ParamArticleComponent, ParamArticleDetailComponent, ParamArticleUpdateComponent, ParamArticleDeleteDialogComponent],
  entryComponents: [ParamArticleDeleteDialogComponent],
})
export class GestionStockJhipsterParamArticleModule {}
