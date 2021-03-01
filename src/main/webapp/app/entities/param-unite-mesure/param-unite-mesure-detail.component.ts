import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IParamUniteMesure } from 'app/shared/model/param-unite-mesure.model';

@Component({
  selector: 'jhi-param-unite-mesure-detail',
  templateUrl: './param-unite-mesure-detail.component.html',
})
export class ParamUniteMesureDetailComponent implements OnInit {
  paramUniteMesure: IParamUniteMesure | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ paramUniteMesure }) => (this.paramUniteMesure = paramUniteMesure));
  }

  previousState(): void {
    window.history.back();
  }
}
