import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IParamCommune } from 'app/shared/model/param-commune.model';

@Component({
  selector: 'jhi-param-commune-detail',
  templateUrl: './param-commune-detail.component.html',
})
export class ParamCommuneDetailComponent implements OnInit {
  paramCommune: IParamCommune | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ paramCommune }) => (this.paramCommune = paramCommune));
  }

  previousState(): void {
    window.history.back();
  }
}
