import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IParamModelArticle } from 'app/shared/model/param-model-article.model';
import { ParamModelArticleService } from './param-model-article.service';
import { ParamModelArticleDeleteDialogComponent } from './param-model-article-delete-dialog.component';

@Component({
  selector: 'jhi-param-model-article',
  templateUrl: './param-model-article.component.html',
})
export class ParamModelArticleComponent implements OnInit, OnDestroy {
  paramModelArticles?: IParamModelArticle[];
  eventSubscriber?: Subscription;

  constructor(
    protected paramModelArticleService: ParamModelArticleService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.paramModelArticleService
      .query()
      .subscribe((res: HttpResponse<IParamModelArticle[]>) => (this.paramModelArticles = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInParamModelArticles();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IParamModelArticle): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInParamModelArticles(): void {
    this.eventSubscriber = this.eventManager.subscribe('paramModelArticleListModification', () => this.loadAll());
  }

  delete(paramModelArticle: IParamModelArticle): void {
    const modalRef = this.modalService.open(ParamModelArticleDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.paramModelArticle = paramModelArticle;
  }
}
