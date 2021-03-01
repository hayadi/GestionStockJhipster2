import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IParamCommune } from 'app/shared/model/param-commune.model';
import { ParamCommuneService } from './param-commune.service';
import { ParamCommuneDeleteDialogComponent } from './param-commune-delete-dialog.component';

@Component({
  selector: 'jhi-param-commune',
  templateUrl: './param-commune.component.html',
})
export class ParamCommuneComponent implements OnInit, OnDestroy {
  paramCommunes?: IParamCommune[];
  eventSubscriber?: Subscription;

  constructor(
    protected paramCommuneService: ParamCommuneService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.paramCommuneService.query().subscribe((res: HttpResponse<IParamCommune[]>) => (this.paramCommunes = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInParamCommunes();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IParamCommune): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInParamCommunes(): void {
    this.eventSubscriber = this.eventManager.subscribe('paramCommuneListModification', () => this.loadAll());
  }

  delete(paramCommune: IParamCommune): void {
    const modalRef = this.modalService.open(ParamCommuneDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.paramCommune = paramCommune;
  }
}
