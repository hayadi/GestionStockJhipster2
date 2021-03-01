import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IAdminProfil, AdminProfil } from 'app/shared/model/admin-profil.model';
import { AdminProfilService } from './admin-profil.service';

@Component({
  selector: 'jhi-admin-profil-update',
  templateUrl: './admin-profil-update.component.html',
})
export class AdminProfilUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    code: [null, [Validators.required]],
    libelle: [null, [Validators.required]],
    description: [],
  });

  constructor(protected adminProfilService: AdminProfilService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ adminProfil }) => {
      this.updateForm(adminProfil);
    });
  }

  updateForm(adminProfil: IAdminProfil): void {
    this.editForm.patchValue({
      id: adminProfil.id,
      code: adminProfil.code,
      libelle: adminProfil.libelle,
      description: adminProfil.description,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const adminProfil = this.createFromForm();
    if (adminProfil.id !== undefined) {
      this.subscribeToSaveResponse(this.adminProfilService.update(adminProfil));
    } else {
      this.subscribeToSaveResponse(this.adminProfilService.create(adminProfil));
    }
  }

  private createFromForm(): IAdminProfil {
    return {
      ...new AdminProfil(),
      id: this.editForm.get(['id'])!.value,
      code: this.editForm.get(['code'])!.value,
      libelle: this.editForm.get(['libelle'])!.value,
      description: this.editForm.get(['description'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAdminProfil>>): void {
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
