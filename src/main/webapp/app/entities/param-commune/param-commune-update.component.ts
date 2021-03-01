import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IParamCommune, ParamCommune } from 'app/shared/model/param-commune.model';
import { ParamCommuneService } from './param-commune.service';
import { IParamWilaya } from 'app/shared/model/param-wilaya.model';
import { ParamWilayaService } from 'app/entities/param-wilaya/param-wilaya.service';

@Component({
  selector: 'jhi-param-commune-update',
  templateUrl: './param-commune-update.component.html',
})
export class ParamCommuneUpdateComponent implements OnInit {
  isSaving = false;
  paramwilayas: IParamWilaya[] = [];

  editForm = this.fb.group({
    id: [],
    code: [null, [Validators.required]],
    libelle: [null, [Validators.required]],
    wilaya: [null, Validators.required],
  });

  constructor(
    protected paramCommuneService: ParamCommuneService,
    protected paramWilayaService: ParamWilayaService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ paramCommune }) => {
      this.updateForm(paramCommune);

      this.paramWilayaService.query().subscribe((res: HttpResponse<IParamWilaya[]>) => (this.paramwilayas = res.body || []));
    });
  }

  updateForm(paramCommune: IParamCommune): void {
    this.editForm.patchValue({
      id: paramCommune.id,
      code: paramCommune.code,
      libelle: paramCommune.libelle,
      wilaya: paramCommune.wilaya,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const paramCommune = this.createFromForm();
    if (paramCommune.id !== undefined) {
      this.subscribeToSaveResponse(this.paramCommuneService.update(paramCommune));
    } else {
      this.subscribeToSaveResponse(this.paramCommuneService.create(paramCommune));
    }
  }

  private createFromForm(): IParamCommune {
    return {
      ...new ParamCommune(),
      id: this.editForm.get(['id'])!.value,
      code: this.editForm.get(['code'])!.value,
      libelle: this.editForm.get(['libelle'])!.value,
      wilaya: this.editForm.get(['wilaya'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IParamCommune>>): void {
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

  trackById(index: number, item: IParamWilaya): any {
    return item.id;
  }
}
