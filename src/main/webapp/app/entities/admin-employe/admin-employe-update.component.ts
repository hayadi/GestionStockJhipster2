import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IAdminEmploye, AdminEmploye } from 'app/shared/model/admin-employe.model';
import { AdminEmployeService } from './admin-employe.service';
import { IAdminProfil } from 'app/shared/model/admin-profil.model';
import { AdminProfilService } from 'app/entities/admin-profil/admin-profil.service';

@Component({
  selector: 'jhi-admin-employe-update',
  templateUrl: './admin-employe-update.component.html',
})
export class AdminEmployeUpdateComponent implements OnInit {
  isSaving = false;
  adminprofils: IAdminProfil[] = [];
  dateNaissanceDp: any;
  dateIntegrationDp: any;
  dateEntreeDp: any;

  editForm = this.fb.group({
    id: [],
    code: [null, [Validators.required]],
    login: [],
    nom: [null, [Validators.required]],
    prenom: [null, [Validators.required]],
    email: [],
    dateNaissance: [null, [Validators.required]],
    dateIntegration: [null, [Validators.required]],
    adresse: [null, [Validators.required]],
    utilisateur: [null, [Validators.required]],
    password: [],
    dateEntree: [],
    profil: [],
  });

  constructor(
    protected adminEmployeService: AdminEmployeService,
    protected adminProfilService: AdminProfilService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ adminEmploye }) => {
      this.updateForm(adminEmploye);

      this.adminProfilService.query().subscribe((res: HttpResponse<IAdminProfil[]>) => (this.adminprofils = res.body || []));
    });
  }

  updateForm(adminEmploye: IAdminEmploye): void {
    this.editForm.patchValue({
      id: adminEmploye.id,
      code: adminEmploye.code,
      login: adminEmploye.login,
      nom: adminEmploye.nom,
      prenom: adminEmploye.prenom,
      email: adminEmploye.email,
      dateNaissance: adminEmploye.dateNaissance,
      dateIntegration: adminEmploye.dateIntegration,
      adresse: adminEmploye.adresse,
      utilisateur: adminEmploye.utilisateur,
      password: adminEmploye.password,
      dateEntree: adminEmploye.dateEntree,
      profil: adminEmploye.profil,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const adminEmploye = this.createFromForm();
    if (adminEmploye.id !== undefined) {
      this.subscribeToSaveResponse(this.adminEmployeService.update(adminEmploye));
    } else {
      this.subscribeToSaveResponse(this.adminEmployeService.create(adminEmploye));
    }
  }

  private createFromForm(): IAdminEmploye {
    return {
      ...new AdminEmploye(),
      id: this.editForm.get(['id'])!.value,
      code: this.editForm.get(['code'])!.value,
      login: this.editForm.get(['login'])!.value,
      nom: this.editForm.get(['nom'])!.value,
      prenom: this.editForm.get(['prenom'])!.value,
      email: this.editForm.get(['email'])!.value,
      dateNaissance: this.editForm.get(['dateNaissance'])!.value,
      dateIntegration: this.editForm.get(['dateIntegration'])!.value,
      adresse: this.editForm.get(['adresse'])!.value,
      utilisateur: this.editForm.get(['utilisateur'])!.value,
      password: this.editForm.get(['password'])!.value,
      dateEntree: this.editForm.get(['dateEntree'])!.value,
      profil: this.editForm.get(['profil'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAdminEmploye>>): void {
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

  trackById(index: number, item: IAdminProfil): any {
    return item.id;
  }
}
