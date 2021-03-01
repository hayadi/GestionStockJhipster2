import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IParamUniteMesure } from 'app/shared/model/param-unite-mesure.model';

type EntityResponseType = HttpResponse<IParamUniteMesure>;
type EntityArrayResponseType = HttpResponse<IParamUniteMesure[]>;

@Injectable({ providedIn: 'root' })
export class ParamUniteMesureService {
  public resourceUrl = SERVER_API_URL + 'api/param-unite-mesures';

  constructor(protected http: HttpClient) {}

  create(paramUniteMesure: IParamUniteMesure): Observable<EntityResponseType> {
    return this.http.post<IParamUniteMesure>(this.resourceUrl, paramUniteMesure, { observe: 'response' });
  }

  update(paramUniteMesure: IParamUniteMesure): Observable<EntityResponseType> {
    return this.http.put<IParamUniteMesure>(this.resourceUrl, paramUniteMesure, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IParamUniteMesure>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IParamUniteMesure[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
