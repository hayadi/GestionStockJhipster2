import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IAdminPrivilegeProfil } from 'app/shared/model/admin-privilege-profil.model';
import { AdminPrivilegeProfilService } from './admin-privilege-profil.service';
import { AdminPrivilegeProfilDeleteDialogComponent } from './admin-privilege-profil-delete-dialog.component';

@Component({
  selector: 'jhi-admin-privilege-profil',
  templateUrl: './admin-privilege-profil.component.html',
})
export class AdminPrivilegeProfilComponent implements OnInit, OnDestroy {
  adminPrivilegeProfils?: IAdminPrivilegeProfil[];
  eventSubscriber?: Subscription;

  constructor(
    protected adminPrivilegeProfilService: AdminPrivilegeProfilService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.adminPrivilegeProfilService
      .query()
      .subscribe((res: HttpResponse<IAdminPrivilegeProfil[]>) => (this.adminPrivilegeProfils = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInAdminPrivilegeProfils();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IAdminPrivilegeProfil): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInAdminPrivilegeProfils(): void {
    this.eventSubscriber = this.eventManager.subscribe('adminPrivilegeProfilListModification', () => this.loadAll());
  }

  delete(adminPrivilegeProfil: IAdminPrivilegeProfil): void {
    const modalRef = this.modalService.open(AdminPrivilegeProfilDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.adminPrivilegeProfil = adminPrivilegeProfil;
  }
}
