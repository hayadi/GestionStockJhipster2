import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAdminEmploye } from 'app/shared/model/admin-employe.model';

@Component({
  selector: 'jhi-admin-employe-detail',
  templateUrl: './admin-employe-detail.component.html',
})
export class AdminEmployeDetailComponent implements OnInit {
  adminEmploye: IAdminEmploye | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ adminEmploye }) => (this.adminEmploye = adminEmploye));
  }

  previousState(): void {
    window.history.back();
  }
}
