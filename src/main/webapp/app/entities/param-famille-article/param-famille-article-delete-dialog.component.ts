import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IParamFamilleArticle } from 'app/shared/model/param-famille-article.model';
import { ParamFamilleArticleService } from './param-famille-article.service';

@Component({
  templateUrl: './param-famille-article-delete-dialog.component.html',
})
export class ParamFamilleArticleDeleteDialogComponent {
  paramFamilleArticle?: IParamFamilleArticle;

  constructor(
    protected paramFamilleArticleService: ParamFamilleArticleService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.paramFamilleArticleService.delete(id).subscribe(() => {
      this.eventManager.broadcast('paramFamilleArticleListModification');
      this.activeModal.close();
    });
  }
}
