import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IParamFournisseur } from 'app/shared/model/param-fournisseur.model';
import { ParamFournisseurService } from './param-fournisseur.service';
import { ParamFournisseurDeleteDialogComponent } from './param-fournisseur-delete-dialog.component';

@Component({
  selector: 'jhi-param-fournisseur',
  templateUrl: './param-fournisseur.component.html',
})
export class ParamFournisseurComponent implements OnInit, OnDestroy {
  paramFournisseurs?: IParamFournisseur[];
  eventSubscriber?: Subscription;

  constructor(
    protected paramFournisseurService: ParamFournisseurService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.paramFournisseurService.query().subscribe((res: HttpResponse<IParamFournisseur[]>) => (this.paramFournisseurs = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInParamFournisseurs();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IParamFournisseur): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInParamFournisseurs(): void {
    this.eventSubscriber = this.eventManager.subscribe('paramFournisseurListModification', () => this.loadAll());
  }

  delete(paramFournisseur: IParamFournisseur): void {
    const modalRef = this.modalService.open(ParamFournisseurDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.paramFournisseur = paramFournisseur;
  }
}
