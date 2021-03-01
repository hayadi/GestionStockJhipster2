import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IParamClient } from 'app/shared/model/param-client.model';
import { ParamClientService } from './param-client.service';

@Component({
  templateUrl: './param-client-delete-dialog.component.html',
})
export class ParamClientDeleteDialogComponent {
  paramClient?: IParamClient;

  constructor(
    protected paramClientService: ParamClientService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.paramClientService.delete(id).subscribe(() => {
      this.eventManager.broadcast('paramClientListModification');
      this.activeModal.close();
    });
  }
}
