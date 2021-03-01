import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IParamUniteMesure, ParamUniteMesure } from 'app/shared/model/param-unite-mesure.model';
import { ParamUniteMesureService } from './param-unite-mesure.service';

@Component({
  selector: 'jhi-param-unite-mesure-update',
  templateUrl: './param-unite-mesure-update.component.html',
})
export class ParamUniteMesureUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    code: [null, [Validators.required]],
    libelle: [null, [Validators.required]],
  });

  constructor(
    protected paramUniteMesureService: ParamUniteMesureService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ paramUniteMesure }) => {
      this.updateForm(paramUniteMesure);
    });
  }

  updateForm(paramUniteMesure: IParamUniteMesure): void {
    this.editForm.patchValue({
      id: paramUniteMesure.id,
      code: paramUniteMesure.code,
      libelle: paramUniteMesure.libelle,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const paramUniteMesure = this.createFromForm();
    if (paramUniteMesure.id !== undefined) {
      this.subscribeToSaveResponse(this.paramUniteMesureService.update(paramUniteMesure));
    } else {
      this.subscribeToSaveResponse(this.paramUniteMesureService.create(paramUniteMesure));
    }
  }

  private createFromForm(): IParamUniteMesure {
    return {
      ...new ParamUniteMesure(),
      id: this.editForm.get(['id'])!.value,
      code: this.editForm.get(['code'])!.value,
      libelle: this.editForm.get(['libelle'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IParamUniteMesure>>): void {
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
