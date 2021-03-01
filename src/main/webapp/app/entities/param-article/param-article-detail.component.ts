import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IParamArticle } from 'app/shared/model/param-article.model';

@Component({
  selector: 'jhi-param-article-detail',
  templateUrl: './param-article-detail.component.html',
})
export class ParamArticleDetailComponent implements OnInit {
  paramArticle: IParamArticle | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ paramArticle }) => (this.paramArticle = paramArticle));
  }

  previousState(): void {
    window.history.back();
  }
}
