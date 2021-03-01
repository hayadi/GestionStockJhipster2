import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IParamFournisseur } from 'app/shared/model/param-fournisseur.model';

@Component({
  selector: 'jhi-param-fournisseur-detail',
  templateUrl: './param-fournisseur-detail.component.html',
})
export class ParamFournisseurDetailComponent implements OnInit {
  paramFournisseur: IParamFournisseur | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ paramFournisseur }) => (this.paramFournisseur = paramFournisseur));
  }

  previousState(): void {
    window.history.back();
  }
}
