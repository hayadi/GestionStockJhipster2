import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAdminPrivilegeProfil } from 'app/shared/model/admin-privilege-profil.model';

@Component({
  selector: 'jhi-admin-privilege-profil-detail',
  templateUrl: './admin-privilege-profil-detail.component.html',
})
export class AdminPrivilegeProfilDetailComponent implements OnInit {
  adminPrivilegeProfil: IAdminPrivilegeProfil | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ adminPrivilegeProfil }) => (this.adminPrivilegeProfil = adminPrivilegeProfil));
  }

  previousState(): void {
    window.history.back();
  }
}
