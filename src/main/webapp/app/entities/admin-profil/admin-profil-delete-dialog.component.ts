import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAdminProfil } from 'app/shared/model/admin-profil.model';
import { AdminProfilService } from './admin-profil.service';

@Component({
  templateUrl: './admin-profil-delete-dialog.component.html',
})
export class AdminProfilDeleteDialogComponent {
  adminProfil?: IAdminProfil;

  constructor(
    protected adminProfilService: AdminProfilService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.adminProfilService.delete(id).subscribe(() => {
      this.eventManager.broadcast('adminProfilListModification');
      this.activeModal.close();
    });
  }
}
