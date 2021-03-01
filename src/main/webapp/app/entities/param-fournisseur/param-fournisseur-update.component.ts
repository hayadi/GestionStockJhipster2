import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IParamFournisseur, ParamFournisseur } from 'app/shared/model/param-fournisseur.model';
import { ParamFournisseurService } from './param-fournisseur.service';

@Component({
  selector: 'jhi-param-fournisseur-update',
  templateUrl: './param-fournisseur-update.component.html',
})
export class ParamFournisseurUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    code: [null, [Validators.required]],
    numeroRegistreCommerce: [null, [Validators.required]],
    nif: [],
    nis: [],
    numArtImposition: [],
    raisonSociale: [null, [Validators.required]],
    telephone: [null, [Validators.required]],
    fax: [],
    email: [],
  });

  constructor(
    protected paramFournisseurService: ParamFournisseurService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ paramFournisseur }) => {
      this.updateForm(paramFournisseur);
    });
  }

  updateForm(paramFournisseur: IParamFournisseur): void {
    this.editForm.patchValue({
      id: paramFournisseur.id,
      code: paramFournisseur.code,
      numeroRegistreCommerce: paramFournisseur.numeroRegistreCommerce,
      nif: paramFournisseur.nif,
      nis: paramFournisseur.nis,
      numArtImposition: paramFournisseur.numArtImposition,
      raisonSociale: paramFournisseur.raisonSociale,
      telephone: paramFournisseur.telephone,
      fax: paramFournisseur.fax,
      email: paramFournisseur.email,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const paramFournisseur = this.createFromForm();
    if (paramFournisseur.id !== undefined) {
      this.subscribeToSaveResponse(this.paramFournisseurService.update(paramFournisseur));
    } else {
      this.subscribeToSaveResponse(this.paramFournisseurService.create(paramFournisseur));
    }
  }

  private createFromForm(): IParamFournisseur {
    return {
      ...new ParamFournisseur(),
      id: this.editForm.get(['id'])!.value,
      code: this.editForm.get(['code'])!.value,
      numeroRegistreCommerce: this.editForm.get(['numeroRegistreCommerce'])!.value,
      nif: this.editForm.get(['nif'])!.value,
      nis: this.editForm.get(['nis'])!.value,
      numArtImposition: this.editForm.get(['numArtImposition'])!.value,
      raisonSociale: this.editForm.get(['raisonSociale'])!.value,
      telephone: this.editForm.get(['telephone'])!.value,
      fax: this.editForm.get(['fax'])!.value,
      email: this.editForm.get(['email'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IParamFournisseur>>): void {
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
