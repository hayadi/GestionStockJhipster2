import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAdminPrivilegeProfil } from 'app/shared/model/admin-privilege-profil.model';
import { AdminPrivilegeProfilService } from './admin-privilege-profil.service';

@Component({
  templateUrl: './admin-privilege-profil-delete-dialog.component.html',
})
export class AdminPrivilegeProfilDeleteDialogComponent {
  adminPrivilegeProfil?: IAdminPrivilegeProfil;

  constructor(
    protected adminPrivilegeProfilService: AdminPrivilegeProfilService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.adminPrivilegeProfilService.delete(id).subscribe(() => {
      this.eventManager.broadcast('adminPrivilegeProfilListModification');
      this.activeModal.close();
    });
  }
}
