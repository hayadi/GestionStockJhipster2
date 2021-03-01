import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAdminEmploye } from 'app/shared/model/admin-employe.model';
import { AdminEmployeService } from './admin-employe.service';

@Component({
  templateUrl: './admin-employe-delete-dialog.component.html',
})
export class AdminEmployeDeleteDialogComponent {
  adminEmploye?: IAdminEmploye;

  constructor(
    protected adminEmployeService: AdminEmployeService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.adminEmployeService.delete(id).subscribe(() => {
      this.eventManager.broadcast('adminEmployeListModification');
      this.activeModal.close();
    });
  }
}
