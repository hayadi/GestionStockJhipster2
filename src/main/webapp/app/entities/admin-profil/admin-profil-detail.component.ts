import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAdminProfil } from 'app/shared/model/admin-profil.model';

@Component({
  selector: 'jhi-admin-profil-detail',
  templateUrl: './admin-profil-detail.component.html',
})
export class AdminProfilDetailComponent implements OnInit {
  adminProfil: IAdminProfil | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ adminProfil }) => (this.adminProfil = adminProfil));
  }

  previousState(): void {
    window.history.back();
  }
}
