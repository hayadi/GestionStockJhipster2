import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IParamUniteMesure } from 'app/shared/model/param-unite-mesure.model';
import { ParamUniteMesureService } from './param-unite-mesure.service';
import { ParamUniteMesureDeleteDialogComponent } from './param-unite-mesure-delete-dialog.component';

@Component({
  selector: 'jhi-param-unite-mesure',
  templateUrl: './param-unite-mesure.component.html',
})
export class ParamUniteMesureComponent implements OnInit, OnDestroy {
  paramUniteMesures?: IParamUniteMesure[];
  eventSubscriber?: Subscription;

  constructor(
    protected paramUniteMesureService: ParamUniteMesureService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.paramUniteMesureService.query().subscribe((res: HttpResponse<IParamUniteMesure[]>) => (this.paramUniteMesures = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInParamUniteMesures();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IParamUniteMesure): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInParamUniteMesures(): void {
    this.eventSubscriber = this.eventManager.subscribe('paramUniteMesureListModification', () => this.loadAll());
  }

  delete(paramUniteMesure: IParamUniteMesure): void {
    const modalRef = this.modalService.open(ParamUniteMesureDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.paramUniteMesure = paramUniteMesure;
  }
}
