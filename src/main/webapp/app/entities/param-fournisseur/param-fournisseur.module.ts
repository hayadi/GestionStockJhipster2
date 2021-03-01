import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GestionStockJhipsterSharedModule } from 'app/shared/shared.module';
import { ParamFournisseurComponent } from './param-fournisseur.component';
import { ParamFournisseurDetailComponent } from './param-fournisseur-detail.component';
import { ParamFournisseurUpdateComponent } from './param-fournisseur-update.component';
import { ParamFournisseurDeleteDialogComponent } from './param-fournisseur-delete-dialog.component';
import { paramFournisseurRoute } from './param-fournisseur.route';

@NgModule({
  imports: [GestionStockJhipsterSharedModule, RouterModule.forChild(paramFournisseurRoute)],
  declarations: [
    ParamFournisseurComponent,
    ParamFournisseurDetailComponent,
    ParamFournisseurUpdateComponent,
    ParamFournisseurDeleteDialogComponent,
  ],
  entryComponents: [ParamFournisseurDeleteDialogComponent],
})
export class GestionStockJhipsterParamFournisseurModule {}
