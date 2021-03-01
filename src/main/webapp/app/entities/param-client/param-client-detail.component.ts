import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IParamClient } from 'app/shared/model/param-client.model';

@Component({
  selector: 'jhi-param-client-detail',
  templateUrl: './param-client-detail.component.html',
})
export class ParamClientDetailComponent implements OnInit {
  paramClient: IParamClient | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ paramClient }) => (this.paramClient = paramClient));
  }

  previousState(): void {
    window.history.back();
  }
}
