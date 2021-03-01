import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IAdminEmploye } from 'app/shared/model/admin-employe.model';
import { AdminEmployeService } from './admin-employe.service';
import { AdminEmployeDeleteDialogComponent } from './admin-employe-delete-dialog.component';

@Component({
  selector: 'jhi-admin-employe',
  templateUrl: './admin-employe.component.html',
})
export class AdminEmployeComponent implements OnInit, OnDestroy {
  adminEmployes?: IAdminEmploye[];
  eventSubscriber?: Subscription;

  constructor(
    protected adminEmployeService: AdminEmployeService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.adminEmployeService.query().subscribe((res: HttpResponse<IAdminEmploye[]>) => (this.adminEmployes = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInAdminEmployes();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IAdminEmploye): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInAdminEmployes(): void {
    this.eventSubscriber = this.eventManager.subscribe('adminEmployeListModification', () => this.loadAll());
  }

  delete(adminEmploye: IAdminEmploye): void {
    const modalRef = this.modalService.open(AdminEmployeDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.adminEmploye = adminEmploye;
  }
}
