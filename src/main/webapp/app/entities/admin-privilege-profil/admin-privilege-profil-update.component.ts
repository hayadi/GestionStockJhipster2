import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IAdminPrivilegeProfil, AdminPrivilegeProfil } from 'app/shared/model/admin-privilege-profil.model';
import { AdminPrivilegeProfilService } from './admin-privilege-profil.service';
import { IAdminProfil } from 'app/shared/model/admin-profil.model';
import { AdminProfilService } from 'app/entities/admin-profil/admin-profil.service';
import { IAdminPrivilege } from 'app/shared/model/admin-privilege.model';
import { AdminPrivilegeService } from 'app/entities/admin-privilege/admin-privilege.service';

type SelectableEntity = IAdminProfil | IAdminPrivilege;

@Component({
  selector: 'jhi-admin-privilege-profil-update',
  templateUrl: './admin-privilege-profil-update.component.html',
})
export class AdminPrivilegeProfilUpdateComponent implements OnInit {
  isSaving = false;
  adminprofils: IAdminProfil[] = [];
  adminprivileges: IAdminPrivilege[] = [];

  editForm = this.fb.group({
    id: [],
    profil: [null, Validators.required],
    privilege: [null, Validators.required],
  });

  constructor(
    protected adminPrivilegeProfilService: AdminPrivilegeProfilService,
    protected adminProfilService: AdminProfilService,
    protected adminPrivilegeService: AdminPrivilegeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ adminPrivilegeProfil }) => {
      this.updateForm(adminPrivilegeProfil);

      this.adminProfilService.query().subscribe((res: HttpResponse<IAdminProfil[]>) => (this.adminprofils = res.body || []));

      this.adminPrivilegeService.query().subscribe((res: HttpResponse<IAdminPrivilege[]>) => (this.adminprivileges = res.body || []));
    });
  }

  updateForm(adminPrivilegeProfil: IAdminPrivilegeProfil): void {
    this.editForm.patchValue({
      id: adminPrivilegeProfil.id,
      profil: adminPrivilegeProfil.profil,
      privilege: adminPrivilegeProfil.privilege,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const adminPrivilegeProfil = this.createFromForm();
    if (adminPrivilegeProfil.id !== undefined) {
      this.subscribeToSaveResponse(this.adminPrivilegeProfilService.update(adminPrivilegeProfil));
    } else {
      this.subscribeToSaveResponse(this.adminPrivilegeProfilService.create(adminPrivilegeProfil));
    }
  }

  private createFromForm(): IAdminPrivilegeProfil {
    return {
      ...new AdminPrivilegeProfil(),
      id: this.editForm.get(['id'])!.value,
      profil: this.editForm.get(['profil'])!.value,
      privilege: this.editForm.get(['privilege'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAdminPrivilegeProfil>>): void {
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
