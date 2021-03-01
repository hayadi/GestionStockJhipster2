import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GestionStockJhipsterSharedModule } from 'app/shared/shared.module';
import { ParamWilayaComponent } from './param-wilaya.component';
import { ParamWilayaDetailComponent } from './param-wilaya-detail.component';
import { ParamWilayaUpdateComponent } from './param-wilaya-update.component';
import { ParamWilayaDeleteDialogComponent } from './param-wilaya-delete-dialog.component';
import { paramWilayaRoute } from './param-wilaya.route';

@NgModule({
  imports: [GestionStockJhipsterSharedModule, RouterModule.forChild(paramWilayaRoute)],
  declarations: [ParamWilayaComponent, ParamWilayaDetailComponent, ParamWilayaUpdateComponent, ParamWilayaDeleteDialogComponent],
  entryComponents: [ParamWilayaDeleteDialogComponent],
})
export class GestionStockJhipsterParamWilayaModule {}
