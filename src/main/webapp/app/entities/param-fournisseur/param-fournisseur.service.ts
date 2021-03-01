import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IParamFournisseur } from 'app/shared/model/param-fournisseur.model';

type EntityResponseType = HttpResponse<IParamFournisseur>;
type EntityArrayResponseType = HttpResponse<IParamFournisseur[]>;

@Injectable({ providedIn: 'root' })
export class ParamFournisseurService {
  public resourceUrl = SERVER_API_URL + 'api/param-fournisseurs';

  constructor(protected http: HttpClient) {}

  create(paramFournisseur: IParamFournisseur): Observable<EntityResponseType> {
    return this.http.post<IParamFournisseur>(this.resourceUrl, paramFournisseur, { observe: 'response' });
  }

  update(paramFournisseur: IParamFournisseur): Observable<EntityResponseType> {
    return this.http.put<IParamFournisseur>(this.resourceUrl, paramFournisseur, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IParamFournisseur>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IParamFournisseur[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
