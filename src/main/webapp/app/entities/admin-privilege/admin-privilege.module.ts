import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GestionStockJhipsterSharedModule } from 'app/shared/shared.module';
import { AdminPrivilegeComponent } from './admin-privilege.component';
import { AdminPrivilegeDetailComponent } from './admin-privilege-detail.component';
import { AdminPrivilegeUpdateComponent } from './admin-privilege-update.component';
import { AdminPrivilegeDeleteDialogComponent } from './admin-privilege-delete-dialog.component';
import { adminPrivilegeRoute } from './admin-privilege.route';

@NgModule({
  imports: [GestionStockJhipsterSharedModule, RouterModule.forChild(adminPrivilegeRoute)],
  declarations: [
    AdminPrivilegeComponent,
    AdminPrivilegeDetailComponent,
    AdminPrivilegeUpdateComponent,
    AdminPrivilegeDeleteDialogComponent,
  ],
  entryComponents: [AdminPrivilegeDeleteDialogComponent],
})
export class GestionStockJhipsterAdminPrivilegeModule {}
