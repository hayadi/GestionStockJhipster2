import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IParamArticle } from 'app/shared/model/param-article.model';
import { ParamArticleService } from './param-article.service';
import { ParamArticleDeleteDialogComponent } from './param-article-delete-dialog.component';

@Component({
  selector: 'jhi-param-article',
  templateUrl: './param-article.component.html',
})
export class ParamArticleComponent implements OnInit, OnDestroy {
  paramArticles?: IParamArticle[];
  eventSubscriber?: Subscription;

  constructor(
    protected paramArticleService: ParamArticleService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.paramArticleService.query().subscribe((res: HttpResponse<IParamArticle[]>) => (this.paramArticles = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInParamArticles();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IParamArticle): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInParamArticles(): void {
    this.eventSubscriber = this.eventManager.subscribe('paramArticleListModification', () => this.loadAll());
  }

  delete(paramArticle: IParamArticle): void {
    const modalRef = this.modalService.open(ParamArticleDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.paramArticle = paramArticle;
  }
}
