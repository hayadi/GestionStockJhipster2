import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAdminPrivilege } from 'app/shared/model/admin-privilege.model';

@Component({
  selector: 'jhi-admin-privilege-detail',
  templateUrl: './admin-privilege-detail.component.html',
})
export class AdminPrivilegeDetailComponent implements OnInit {
  adminPrivilege: IAdminPrivilege | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ adminPrivilege }) => (this.adminPrivilege = adminPrivilege));
  }

  previousState(): void {
    window.history.back();
  }
}
