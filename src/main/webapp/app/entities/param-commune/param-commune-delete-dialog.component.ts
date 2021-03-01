import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IParamCommune } from 'app/shared/model/param-commune.model';
import { ParamCommuneService } from './param-commune.service';

@Component({
  templateUrl: './param-commune-delete-dialog.component.html',
})
export class ParamCommuneDeleteDialogComponent {
  paramCommune?: IParamCommune;

  constructor(
    protected paramCommuneService: ParamCommuneService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.paramCommuneService.delete(id).subscribe(() => {
      this.eventManager.broadcast('paramCommuneListModification');
      this.activeModal.close();
    });
  }
}
