import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GestionStockJhipsterSharedModule } from 'app/shared/shared.module';
import { AdminEmployeComponent } from './admin-employe.component';
import { AdminEmployeDetailComponent } from './admin-employe-detail.component';
import { AdminEmployeUpdateComponent } from './admin-employe-update.component';
import { AdminEmployeDeleteDialogComponent } from './admin-employe-delete-dialog.component';
import { adminEmployeRoute } from './admin-employe.route';

@NgModule({
  imports: [GestionStockJhipsterSharedModule, RouterModule.forChild(adminEmployeRoute)],
  declarations: [AdminEmployeComponent, AdminEmployeDetailComponent, AdminEmployeUpdateComponent, AdminEmployeDeleteDialogComponent],
  entryComponents: [AdminEmployeDeleteDialogComponent],
})
export class GestionStockJhipsterAdminEmployeModule {}
