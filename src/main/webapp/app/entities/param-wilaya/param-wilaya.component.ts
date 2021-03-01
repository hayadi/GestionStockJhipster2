import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IParamWilaya } from 'app/shared/model/param-wilaya.model';
import { ParamWilayaService } from './param-wilaya.service';
import { ParamWilayaDeleteDialogComponent } from './param-wilaya-delete-dialog.component';

@Component({
  selector: 'jhi-param-wilaya',
  templateUrl: './param-wilaya.component.html',
})
export class ParamWilayaComponent implements OnInit, OnDestroy {
  paramWilayas?: IParamWilaya[];
  eventSubscriber?: Subscription;

  constructor(
    protected paramWilayaService: ParamWilayaService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.paramWilayaService.query().subscribe((res: HttpResponse<IParamWilaya[]>) => (this.paramWilayas = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInParamWilayas();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IParamWilaya): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInParamWilayas(): void {
    this.eventSubscriber = this.eventManager.subscribe('paramWilayaListModification', () => this.loadAll());
  }

  delete(paramWilaya: IParamWilaya): void {
    const modalRef = this.modalService.open(ParamWilayaDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.paramWilaya = paramWilaya;
  }
}
