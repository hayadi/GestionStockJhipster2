import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IParamClient } from 'app/shared/model/param-client.model';
import { ParamClientService } from './param-client.service';
import { ParamClientDeleteDialogComponent } from './param-client-delete-dialog.component';

@Component({
  selector: 'jhi-param-client',
  templateUrl: './param-client.component.html',
})
export class ParamClientComponent implements OnInit, OnDestroy {
  paramClients?: IParamClient[];
  eventSubscriber?: Subscription;

  constructor(
    protected paramClientService: ParamClientService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.paramClientService.query().subscribe((res: HttpResponse<IParamClient[]>) => (this.paramClients = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInParamClients();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IParamClient): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInParamClients(): void {
    this.eventSubscriber = this.eventManager.subscribe('paramClientListModification', () => this.loadAll());
  }

  delete(paramClient: IParamClient): void {
    const modalRef = this.modalService.open(ParamClientDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.paramClient = paramClient;
  }
}
