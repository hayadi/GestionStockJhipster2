import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IParamArticle } from 'app/shared/model/param-article.model';
import { ParamArticleService } from './param-article.service';

@Component({
  templateUrl: './param-article-delete-dialog.component.html',
})
export class ParamArticleDeleteDialogComponent {
  paramArticle?: IParamArticle;

  constructor(
    protected paramArticleService: ParamArticleService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.paramArticleService.delete(id).subscribe(() => {
      this.eventManager.broadcast('paramArticleListModification');
      this.activeModal.close();
    });
  }
}
