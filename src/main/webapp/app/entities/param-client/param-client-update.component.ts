import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IParamClient, ParamClient } from 'app/shared/model/param-client.model';
import { ParamClientService } from './param-client.service';

@Component({
  selector: 'jhi-param-client-update',
  templateUrl: './param-client-update.component.html',
})
export class ParamClientUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    code: [null, [Validators.required]],
    nom: [],
    prenom: [],
    raisonSociale: [],
    numeroRegistreCommerce: [],
    nif: [null, [Validators.required]],
    nis: [null, [Validators.required]],
    numArtImposition: [],
    telephone: [],
    fax: [],
    email: [],
  });

  constructor(protected paramClientService: ParamClientService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ paramClient }) => {
      this.updateForm(paramClient);
    });
  }

  updateForm(paramClient: IParamClient): void {
    this.editForm.patchValue({
      id: paramClient.id,
      code: paramClient.code,
      nom: paramClient.nom,
      prenom: paramClient.prenom,
      raisonSociale: paramClient.raisonSociale,
      numeroRegistreCommerce: paramClient.numeroRegistreCommerce,
      nif: paramClient.nif,
      nis: paramClient.nis,
      numArtImposition: paramClient.numArtImposition,
      telephone: paramClient.telephone,
      fax: paramClient.fax,
      email: paramClient.email,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const paramClient = this.createFromForm();
    if (paramClient.id !== undefined) {
      this.subscribeToSaveResponse(this.paramClientService.update(paramClient));
    } else {
      this.subscribeToSaveResponse(this.paramClientService.create(paramClient));
    }
  }

  private createFromForm(): IParamClient {
    return {
      ...new ParamClient(),
      id: this.editForm.get(['id'])!.value,
      code: this.editForm.get(['code'])!.value,
      nom: this.editForm.get(['nom'])!.value,
      prenom: this.editForm.get(['prenom'])!.value,
      raisonSociale: this.editForm.get(['raisonSociale'])!.value,
      numeroRegistreCommerce: this.editForm.get(['numeroRegistreCommerce'])!.value,
      nif: this.editForm.get(['nif'])!.value,
      nis: this.editForm.get(['nis'])!.value,
      numArtImposition: this.editForm.get(['numArtImposition'])!.value,
      telephone: this.editForm.get(['telephone'])!.value,
      fax: this.editForm.get(['fax'])!.value,
      email: this.editForm.get(['email'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IParamClient>>): void {
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
