import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAdminPrivilege } from 'app/shared/model/admin-privilege.model';
import { AdminPrivilegeService } from './admin-privilege.service';

@Component({
  templateUrl: './admin-privilege-delete-dialog.component.html',
})
export class AdminPrivilegeDeleteDialogComponent {
  adminPrivilege?: IAdminPrivilege;

  constructor(
    protected adminPrivilegeService: AdminPrivilegeService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.adminPrivilegeService.delete(id).subscribe(() => {
      this.eventManager.broadcast('adminPrivilegeListModification');
      this.activeModal.close();
    });
  }
}
