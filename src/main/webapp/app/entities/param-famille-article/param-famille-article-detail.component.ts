import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IParamFamilleArticle } from 'app/shared/model/param-famille-article.model';

@Component({
  selector: 'jhi-param-famille-article-detail',
  templateUrl: './param-famille-article-detail.component.html',
})
export class ParamFamilleArticleDetailComponent implements OnInit {
  paramFamilleArticle: IParamFamilleArticle | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ paramFamilleArticle }) => (this.paramFamilleArticle = paramFamilleArticle));
  }

  previousState(): void {
    window.history.back();
  }
}
