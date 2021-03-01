import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IAdminPrivilege } from 'app/shared/model/admin-privilege.model';
import { AdminPrivilegeService } from './admin-privilege.service';
import { AdminPrivilegeDeleteDialogComponent } from './admin-privilege-delete-dialog.component';

@Component({
  selector: 'jhi-admin-privilege',
  templateUrl: './admin-privilege.component.html',
})
export class AdminPrivilegeComponent implements OnInit, OnDestroy {
  adminPrivileges?: IAdminPrivilege[];
  eventSubscriber?: Subscription;

  constructor(
    protected adminPrivilegeService: AdminPrivilegeService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.adminPrivilegeService.query().subscribe((res: HttpResponse<IAdminPrivilege[]>) => (this.adminPrivileges = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInAdminPrivileges();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IAdminPrivilege): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInAdminPrivileges(): void {
    this.eventSubscriber = this.eventManager.subscribe('adminPrivilegeListModification', () => this.loadAll());
  }

  delete(adminPrivilege: IAdminPrivilege): void {
    const modalRef = this.modalService.open(AdminPrivilegeDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.adminPrivilege = adminPrivilege;
  }
}
