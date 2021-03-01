import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IParamWilaya } from 'app/shared/model/param-wilaya.model';
import { ParamWilayaService } from './param-wilaya.service';

@Component({
  templateUrl: './param-wilaya-delete-dialog.component.html',
})
export class ParamWilayaDeleteDialogComponent {
  paramWilaya?: IParamWilaya;

  constructor(
    protected paramWilayaService: ParamWilayaService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.paramWilayaService.delete(id).subscribe(() => {
      this.eventManager.broadcast('paramWilayaListModification');
      this.activeModal.close();
    });
  }
}
