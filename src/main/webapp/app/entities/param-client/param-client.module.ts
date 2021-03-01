import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GestionStockJhipsterSharedModule } from 'app/shared/shared.module';
import { ParamClientComponent } from './param-client.component';
import { ParamClientDetailComponent } from './param-client-detail.component';
import { ParamClientUpdateComponent } from './param-client-update.component';
import { ParamClientDeleteDialogComponent } from './param-client-delete-dialog.component';
import { paramClientRoute } from './param-client.route';

@NgModule({
  imports: [GestionStockJhipsterSharedModule, RouterModule.forChild(paramClientRoute)],
  declarations: [ParamClientComponent, ParamClientDetailComponent, ParamClientUpdateComponent, ParamClientDeleteDialogComponent],
  entryComponents: [ParamClientDeleteDialogComponent],
})
export class GestionStockJhipsterParamClientModule {}
