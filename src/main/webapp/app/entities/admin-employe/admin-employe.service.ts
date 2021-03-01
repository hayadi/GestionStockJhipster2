import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IAdminEmploye } from 'app/shared/model/admin-employe.model';

type EntityResponseType = HttpResponse<IAdminEmploye>;
type EntityArrayResponseType = HttpResponse<IAdminEmploye[]>;

@Injectable({ providedIn: 'root' })
export class AdminEmployeService {
  public resourceUrl = SERVER_API_URL + 'api/admin-employes';

  constructor(protected http: HttpClient) {}

  create(adminEmploye: IAdminEmploye): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(adminEmploye);
    return this.http
      .post<IAdminEmploye>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(adminEmploye: IAdminEmploye): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(adminEmploye);
    return this.http
      .put<IAdminEmploye>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IAdminEmploye>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IAdminEmploye[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(adminEmploye: IAdminEmploye): IAdminEmploye {
    const copy: IAdminEmploye = Object.assign({}, adminEmploye, {
      dateNaissance:
        adminEmploye.dateNaissance && adminEmploye.dateNaissance.isValid() ? adminEmploye.dateNaissance.format(DATE_FORMAT) : undefined,
      dateIntegration:
        adminEmploye.dateIntegration && adminEmploye.dateIntegration.isValid()
          ? adminEmploye.dateIntegration.format(DATE_FORMAT)
          : undefined,
      dateEntree: adminEmploye.dateEntree && adminEmploye.dateEntree.isValid() ? adminEmploye.dateEntree.format(DATE_FORMAT) : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateNaissance = res.body.dateNaissance ? moment(res.body.dateNaissance) : undefined;
      res.body.dateIntegration = res.body.dateIntegration ? moment(res.body.dateIntegration) : undefined;
      res.body.dateEntree = res.body.dateEntree ? moment(res.body.dateEntree) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((adminEmploye: IAdminEmploye) => {
        adminEmploye.dateNaissance = adminEmploye.dateNaissance ? moment(adminEmploye.dateNaissance) : undefined;
        adminEmploye.dateIntegration = adminEmploye.dateIntegration ? moment(adminEmploye.dateIntegration) : undefined;
        adminEmploye.dateEntree = adminEmploye.dateEntree ? moment(adminEmploye.dateEntree) : undefined;
      });
    }
    return res;
  }
}
