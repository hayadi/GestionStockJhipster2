import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GestionStockJhipsterSharedModule } from 'app/shared/shared.module';
import { AdminProfilComponent } from './admin-profil.component';
import { AdminProfilDetailComponent } from './admin-profil-detail.component';
import { AdminProfilUpdateComponent } from './admin-profil-update.component';
import { AdminProfilDeleteDialogComponent } from './admin-profil-delete-dialog.component';
import { adminProfilRoute } from './admin-profil.route';

@NgModule({
  imports: [GestionStockJhipsterSharedModule, RouterModule.forChild(adminProfilRoute)],
  declarations: [AdminProfilComponent, AdminProfilDetailComponent, AdminProfilUpdateComponent, AdminProfilDeleteDialogComponent],
  entryComponents: [AdminProfilDeleteDialogComponent],
})
export class GestionStockJhipsterAdminProfilModule {}
