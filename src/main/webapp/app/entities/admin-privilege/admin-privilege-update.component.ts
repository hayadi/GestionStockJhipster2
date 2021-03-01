import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IAdminPrivilege, AdminPrivilege } from 'app/shared/model/admin-privilege.model';
import { AdminPrivilegeService } from './admin-privilege.service';

@Component({
  selector: 'jhi-admin-privilege-update',
  templateUrl: './admin-privilege-update.component.html',
})
export class AdminPrivilegeUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    code: [null, [Validators.required]],
    libelle: [null, [Validators.required]],
    description: [],
  });

  constructor(protected adminPrivilegeService: AdminPrivilegeService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ adminPrivilege }) => {
      this.updateForm(adminPrivilege);
    });
  }

  updateForm(adminPrivilege: IAdminPrivilege): void {
    this.editForm.patchValue({
      id: adminPrivilege.id,
      code: adminPrivilege.code,
      libelle: adminPrivilege.libelle,
      description: adminPrivilege.description,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const adminPrivilege = this.createFromForm();
    if (adminPrivilege.id !== undefined) {
      this.subscribeToSaveResponse(this.adminPrivilegeService.update(adminPrivilege));
    } else {
      this.subscribeToSaveResponse(this.adminPrivilegeService.create(adminPrivilege));
    }
  }

  private createFromForm(): IAdminPrivilege {
    return {
      ...new AdminPrivilege(),
      id: this.editForm.get(['id'])!.value,
      code: this.editForm.get(['code'])!.value,
      libelle: this.editForm.get(['libelle'])!.value,
      description: this.editForm.get(['description'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAdminPrivilege>>): void {
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
