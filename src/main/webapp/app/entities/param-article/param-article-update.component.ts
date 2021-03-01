import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IParamArticle, ParamArticle } from 'app/shared/model/param-article.model';
import { ParamArticleService } from './param-article.service';
import { IParamFamilleArticle } from 'app/shared/model/param-famille-article.model';
import { ParamFamilleArticleService } from 'app/entities/param-famille-article/param-famille-article.service';
import { IParamUniteMesure } from 'app/shared/model/param-unite-mesure.model';
import { ParamUniteMesureService } from 'app/entities/param-unite-mesure/param-unite-mesure.service';

type SelectableEntity = IParamFamilleArticle | IParamUniteMesure;

@Component({
  selector: 'jhi-param-article-update',
  templateUrl: './param-article-update.component.html',
})
export class ParamArticleUpdateComponent implements OnInit {
  isSaving = false;
  paramfamillearticles: IParamFamilleArticle[] = [];
  paramunitemesures: IParamUniteMesure[] = [];

  editForm = this.fb.group({
    id: [],
    code: [null, [Validators.required]],
    libelle: [null, [Validators.required]],
    description: [],
    consommable: [],
    quantiteSeuil: [],
    garantieSeuil: [],
    expirationSeuil: [],
    expirable: [],
    famille: [null, Validators.required],
    unite: [null, Validators.required],
  });

  constructor(
    protected paramArticleService: ParamArticleService,
    protected paramFamilleArticleService: ParamFamilleArticleService,
    protected paramUniteMesureService: ParamUniteMesureService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ paramArticle }) => {
      this.updateForm(paramArticle);

      this.paramFamilleArticleService
        .query()
        .subscribe((res: HttpResponse<IParamFamilleArticle[]>) => (this.paramfamillearticles = res.body || []));

      this.paramUniteMesureService.query().subscribe((res: HttpResponse<IParamUniteMesure[]>) => (this.paramunitemesures = res.body || []));
    });
  }

  updateForm(paramArticle: IParamArticle): void {
    this.editForm.patchValue({
      id: paramArticle.id,
      code: paramArticle.code,
      libelle: paramArticle.libelle,
      description: paramArticle.description,
      consommable: paramArticle.consommable,
      quantiteSeuil: paramArticle.quantiteSeuil,
      garantieSeuil: paramArticle.garantieSeuil,
      expirationSeuil: paramArticle.expirationSeuil,
      expirable: paramArticle.expirable,
      famille: paramArticle.famille,
      unite: paramArticle.unite,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const paramArticle = this.createFromForm();
    if (paramArticle.id !== undefined) {
      this.subscribeToSaveResponse(this.paramArticleService.update(paramArticle));
    } else {
      this.subscribeToSaveResponse(this.paramArticleService.create(paramArticle));
    }
  }

  private createFromForm(): IParamArticle {
    return {
      ...new ParamArticle(),
      id: this.editForm.get(['id'])!.value,
      code: this.editForm.get(['code'])!.value,
      libelle: this.editForm.get(['libelle'])!.value,
      description: this.editForm.get(['description'])!.value,
      consommable: this.editForm.get(['consommable'])!.value,
      quantiteSeuil: this.editForm.get(['quantiteSeuil'])!.value,
      garantieSeuil: this.editForm.get(['garantieSeuil'])!.value,
      expirationSeuil: this.editForm.get(['expirationSeuil'])!.value,
      expirable: this.editForm.get(['expirable'])!.value,
      famille: this.editForm.get(['famille'])!.value,
      unite: this.editForm.get(['unite'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IParamArticle>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
