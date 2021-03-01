import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GestionStockJhipsterSharedModule } from 'app/shared/shared.module';
import { ParamCommuneComponent } from './param-commune.component';
import { ParamCommuneDetailComponent } from './param-commune-detail.component';
import { ParamCommuneUpdateComponent } from './param-commune-update.component';
import { ParamCommuneDeleteDialogComponent } from './param-commune-delete-dialog.component';
import { paramCommuneRoute } from './param-commune.route';

@NgModule({
  imports: [GestionStockJhipsterSharedModule, RouterModule.forChild(paramCommuneRoute)],
  declarations: [ParamCommuneComponent, ParamCommuneDetailComponent, ParamCommuneUpdateComponent, ParamCommuneDeleteDialogComponent],
  entryComponents: [ParamCommuneDeleteDialogComponent],
})
export class GestionStockJhipsterParamCommuneModule {}
