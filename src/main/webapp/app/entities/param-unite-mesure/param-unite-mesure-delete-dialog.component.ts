import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IParamUniteMesure } from 'app/shared/model/param-unite-mesure.model';
import { ParamUniteMesureService } from './param-unite-mesure.service';

@Component({
  templateUrl: './param-unite-mesure-delete-dialog.component.html',
})
export class ParamUniteMesureDeleteDialogComponent {
  paramUniteMesure?: IParamUniteMesure;

  constructor(
    protected paramUniteMesureService: ParamUniteMesureService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.paramUniteMesureService.delete(id).subscribe(() => {
      this.eventManager.broadcast('paramUniteMesureListModification');
      this.activeModal.close();
    });
  }
}
