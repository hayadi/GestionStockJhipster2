import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IAdminProfil } from 'app/shared/model/admin-profil.model';
import { AdminProfilService } from './admin-profil.service';
import { AdminProfilDeleteDialogComponent } from './admin-profil-delete-dialog.component';

@Component({
  selector: 'jhi-admin-profil',
  templateUrl: './admin-profil.component.html',
})
export class AdminProfilComponent implements OnInit, OnDestroy {
  adminProfils?: IAdminProfil[];
  eventSubscriber?: Subscription;

  constructor(
    protected adminProfilService: AdminProfilService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.adminProfilService.query().subscribe((res: HttpResponse<IAdminProfil[]>) => (this.adminProfils = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInAdminProfils();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IAdminProfil): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInAdminProfils(): void {
    this.eventSubscriber = this.eventManager.subscribe('adminProfilListModification', () => this.loadAll());
  }

  delete(adminProfil: IAdminProfil): void {
    const modalRef = this.modalService.open(AdminProfilDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.adminProfil = adminProfil;
  }
}
