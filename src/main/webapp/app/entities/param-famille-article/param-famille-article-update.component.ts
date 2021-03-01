import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IParamFamilleArticle, ParamFamilleArticle } from 'app/shared/model/param-famille-article.model';
import { ParamFamilleArticleService } from './param-famille-article.service';

@Component({
  selector: 'jhi-param-famille-article-update',
  templateUrl: './param-famille-article-update.component.html',
})
export class ParamFamilleArticleUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    code: [null, [Validators.required]],
    libelle: [null, [Validators.required]],
  });

  constructor(
    protected paramFamilleArticleService: ParamFamilleArticleService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ paramFamilleArticle }) => {
      this.updateForm(paramFamilleArticle);
    });
  }

  updateForm(paramFamilleArticle: IParamFamilleArticle): void {
    this.editForm.patchValue({
      id: paramFamilleArticle.id,
      code: paramFamilleArticle.code,
      libelle: paramFamilleArticle.libelle,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const paramFamilleArticle = this.createFromForm();
    if (paramFamilleArticle.id !== undefined) {
      this.subscribeToSaveResponse(this.paramFamilleArticleService.update(paramFamilleArticle));
    } else {
      this.subscribeToSaveResponse(this.paramFamilleArticleService.create(paramFamilleArticle));
    }
  }

  private createFromForm(): IParamFamilleArticle {
    return {
      ...new ParamFamilleArticle(),
      id: this.editForm.get(['id'])!.value,
      code: this.editForm.get(['code'])!.value,
      libelle: this.editForm.get(['libelle'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IParamFamilleArticle>>): void {
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
}
