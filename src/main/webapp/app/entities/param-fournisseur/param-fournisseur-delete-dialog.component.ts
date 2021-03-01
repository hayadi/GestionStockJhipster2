import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IParamFournisseur } from 'app/shared/model/param-fournisseur.model';
import { ParamFournisseurService } from './param-fournisseur.service';

@Component({
  templateUrl: './param-fournisseur-delete-dialog.component.html',
})
export class ParamFournisseurDeleteDialogComponent {
  paramFournisseur?: IParamFournisseur;

  constructor(
    protected paramFournisseurService: ParamFournisseurService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.paramFournisseurService.delete(id).subscribe(() => {
      this.eventManager.broadcast('paramFournisseurListModification');
      this.activeModal.close();
    });
  }
}
