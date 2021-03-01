import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IParamModelArticle } from 'app/shared/model/param-model-article.model';

@Component({
  selector: 'jhi-param-model-article-detail',
  templateUrl: './param-model-article-detail.component.html',
})
export class ParamModelArticleDetailComponent implements OnInit {
  paramModelArticle: IParamModelArticle | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ paramModelArticle }) => (this.paramModelArticle = paramModelArticle));
  }

  previousState(): void {
    window.history.back();
  }
}
