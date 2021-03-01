import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IParamModelArticle } from 'app/shared/model/param-model-article.model';
import { ParamModelArticleService } from './param-model-article.service';

@Component({
  templateUrl: './param-model-article-delete-dialog.component.html',
})
export class ParamModelArticleDeleteDialogComponent {
  paramModelArticle?: IParamModelArticle;

  constructor(
    protected paramModelArticleService: ParamModelArticleService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.paramModelArticleService.delete(id).subscribe(() => {
      this.eventManager.broadcast('paramModelArticleListModification');
      this.activeModal.close();
    });
  }
}
