import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IParamWilaya } from 'app/shared/model/param-wilaya.model';

@Component({
  selector: 'jhi-param-wilaya-detail',
  templateUrl: './param-wilaya-detail.component.html',
})
export class ParamWilayaDetailComponent implements OnInit {
  paramWilaya: IParamWilaya | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ paramWilaya }) => (this.paramWilaya = paramWilaya));
  }

  previousState(): void {
    window.history.back();
  }
}
