import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GestionStockJhipsterSharedModule } from 'app/shared/shared.module';
import { ParamUniteMesureComponent } from './param-unite-mesure.component';
import { ParamUniteMesureDetailComponent } from './param-unite-mesure-detail.component';
import { ParamUniteMesureUpdateComponent } from './param-unite-mesure-update.component';
import { ParamUniteMesureDeleteDialogComponent } from './param-unite-mesure-delete-dialog.component';
import { paramUniteMesureRoute } from './param-unite-mesure.route';

@NgModule({
  imports: [GestionStockJhipsterSharedModule, RouterModule.forChild(paramUniteMesureRoute)],
  declarations: [
    ParamUniteMesureComponent,
    ParamUniteMesureDetailComponent,
    ParamUniteMesureUpdateComponent,
    ParamUniteMesureDeleteDialogComponent,
  ],
  entryComponents: [ParamUniteMesureDeleteDialogComponent],
})
export class GestionStockJhipsterParamUniteMesureModule {}
