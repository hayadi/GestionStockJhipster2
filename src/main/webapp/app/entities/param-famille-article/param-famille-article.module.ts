import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GestionStockJhipsterSharedModule } from 'app/shared/shared.module';
import { ParamFamilleArticleComponent } from './param-famille-article.component';
import { ParamFamilleArticleDetailComponent } from './param-famille-article-detail.component';
import { ParamFamilleArticleUpdateComponent } from './param-famille-article-update.component';
import { ParamFamilleArticleDeleteDialogComponent } from './param-famille-article-delete-dialog.component';
import { paramFamilleArticleRoute } from './param-famille-article.route';

@NgModule({
  imports: [GestionStockJhipsterSharedModule, RouterModule.forChild(paramFamilleArticleRoute)],
  declarations: [
    ParamFamilleArticleComponent,
    ParamFamilleArticleDetailComponent,
    ParamFamilleArticleUpdateComponent,
    ParamFamilleArticleDeleteDialogComponent,
  ],
  entryComponents: [ParamFamilleArticleDeleteDialogComponent],
})
export class GestionStockJhipsterParamFamilleArticleModule {}
