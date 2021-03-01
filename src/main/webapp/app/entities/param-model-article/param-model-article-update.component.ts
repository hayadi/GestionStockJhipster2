import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IParamModelArticle, ParamModelArticle } from 'app/shared/model/param-model-article.model';
import { ParamModelArticleService } from './param-model-article.service';
import { IParamArticle } from 'app/shared/model/param-article.model';
import { ParamArticleService } from 'app/entities/param-article/param-article.service';

@Component({
  selector: 'jhi-param-model-article-update',
  templateUrl: './param-model-article-update.component.html',
})
export class ParamModelArticleUpdateComponent implements OnInit {
  isSaving = false;
  paramarticles: IParamArticle[] = [];

  editForm = this.fb.group({
    id: [],
    code: [null, [Validators.required]],
    libelle: [null, [Validators.required]],
    urlImage: [],
    article: [null, Validators.required],
  });

  constructor(
    protected paramModelArticleService: ParamModelArticleService,
    protected paramArticleService: ParamArticleService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ paramModelArticle }) => {
      this.updateForm(paramModelArticle);

      this.paramArticleService.query().subscribe((res: HttpResponse<IParamArticle[]>) => (this.paramarticles = res.body || []));
    });
  }

  updateForm(paramModelArticle: IParamModelArticle): void {
    this.editForm.patchValue({
      id: paramModelArticle.id,
      code: paramModelArticle.code,
      libelle: paramModelArticle.libelle,
      urlImage: paramModelArticle.urlImage,
      article: paramModelArticle.article,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const paramModelArticle = this.createFromForm();
    if (paramModelArticle.id !== undefined) {
      this.subscribeToSaveResponse(this.paramModelArticleService.update(paramModelArticle));
    } else {
      this.subscribeToSaveResponse(this.paramModelArticleService.create(paramModelArticle));
    }
  }

  private createFromForm(): IParamModelArticle {
    return {
      ...new ParamModelArticle(),
      id: this.editForm.get(['id'])!.value,
      code: this.editForm.get(['code'])!.value,
      libelle: this.editForm.get(['libelle'])!.value,
      urlImage: this.editForm.get(['urlImage'])!.value,
      article: this.editForm.get(['article'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IParamModelArticle>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: IParamArticle): any {
    return item.id;
  }
}
