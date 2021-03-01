import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IParamWilaya, ParamWilaya } from 'app/shared/model/param-wilaya.model';
import { ParamWilayaService } from './param-wilaya.service';

@Component({
  selector: 'jhi-param-wilaya-update',
  templateUrl: './param-wilaya-update.component.html',
})
export class ParamWilayaUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    code: [null, [Validators.required]],
    libelle: [null, [Validators.required]],
  });

  constructor(protected paramWilayaService: ParamWilayaService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ paramWilaya }) => {
      this.updateForm(paramWilaya);
    });
  }

  updateForm(paramWilaya: IParamWilaya): void {
    this.editForm.patchValue({
      id: paramWilaya.id,
      code: paramWilaya.code,
      libelle: paramWilaya.libelle,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const paramWilaya = this.createFromForm();
    if (paramWilaya.id !== undefined) {
      this.subscribeToSaveResponse(this.paramWilayaService.update(paramWilaya));
    } else {
      this.subscribeToSaveResponse(this.paramWilayaService.create(paramWilaya));
    }
  }

  private createFromForm(): IParamWilaya {
    return {
      ...new ParamWilaya(),
      id: this.editForm.get(['id'])!.value,
      code: this.editForm.get(['code'])!.value,
      libelle: this.editForm.get(['libelle'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IParamWilaya>>): void {
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
