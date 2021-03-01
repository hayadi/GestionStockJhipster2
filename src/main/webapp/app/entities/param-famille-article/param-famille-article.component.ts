import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IParamFamilleArticle } from 'app/shared/model/param-famille-article.model';
import { ParamFamilleArticleService } from './param-famille-article.service';
import { ParamFamilleArticleDeleteDialogComponent } from './param-famille-article-delete-dialog.component';

@Component({
  selector: 'jhi-param-famille-article',
  templateUrl: './param-famille-article.component.html',
})
export class ParamFamilleArticleComponent implements OnInit, OnDestroy {
  paramFamilleArticles?: IParamFamilleArticle[];
  eventSubscriber?: Subscription;

  constructor(
    protected paramFamilleArticleService: ParamFamilleArticleService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.paramFamilleArticleService
      .query()
      .subscribe((res: HttpResponse<IParamFamilleArticle[]>) => (this.paramFamilleArticles = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInParamFamilleArticles();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IParamFamilleArticle): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInParamFamilleArticles(): void {
    this.eventSubscriber = this.eventManager.subscribe('paramFamilleArticleListModification', () => this.loadAll());
  }

  delete(paramFamilleArticle: IParamFamilleArticle): void {
    const modalRef = this.modalService.open(ParamFamilleArticleDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.paramFamilleArticle = paramFamilleArticle;
  }
}
