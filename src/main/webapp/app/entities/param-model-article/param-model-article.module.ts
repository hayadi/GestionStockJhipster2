import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GestionStockJhipsterSharedModule } from 'app/shared/shared.module';
import { ParamModelArticleComponent } from './param-model-article.component';
import { ParamModelArticleDetailComponent } from './param-model-article-detail.component';
import { ParamModelArticleUpdateComponent } from './param-model-article-update.component';
import { ParamModelArticleDeleteDialogComponent } from './param-model-article-delete-dialog.component';
import { paramModelArticleRoute } from './param-model-article.route';

@NgModule({
  imports: [GestionStockJhipsterSharedModule, RouterModule.forChild(paramModelArticleRoute)],
  declarations: [
    ParamModelArticleComponent,
    ParamModelArticleDetailComponent,
    ParamModelArticleUpdateComponent,
    ParamModelArticleDeleteDialogComponent,
  ],
  entryComponents: [ParamModelArticleDeleteDialogComponent],
})
export class GestionStockJhipsterParamModelArticleModule {}
