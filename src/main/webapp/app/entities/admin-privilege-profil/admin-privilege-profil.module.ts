import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GestionStockJhipsterSharedModule } from 'app/shared/shared.module';
import { AdminPrivilegeProfilComponent } from './admin-privilege-profil.component';
import { AdminPrivilegeProfilDetailComponent } from './admin-privilege-profil-detail.component';
import { AdminPrivilegeProfilUpdateComponent } from './admin-privilege-profil-update.component';
import { AdminPrivilegeProfilDeleteDialogComponent } from './admin-privilege-profil-delete-dialog.component';
import { adminPrivilegeProfilRoute } from './admin-privilege-profil.route';

@NgModule({
  imports: [GestionStockJhipsterSharedModule, RouterModule.forChild(adminPrivilegeProfilRoute)],
  declarations: [
    AdminPrivilegeProfilComponent,
    AdminPrivilegeProfilDetailComponent,
    AdminPrivilegeProfilUpdateComponent,
    AdminPrivilegeProfilDeleteDialogComponent,
  ],
  entryComponents: [AdminPrivilegeProfilDeleteDialogComponent],
})
export class GestionStockJhipsterAdminPrivilegeProfilModule {}
