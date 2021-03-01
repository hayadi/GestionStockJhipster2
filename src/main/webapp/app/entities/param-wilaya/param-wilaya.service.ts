import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IParamWilaya } from 'app/shared/model/param-wilaya.model';

type EntityResponseType = HttpResponse<IParamWilaya>;
type EntityArrayResponseType = HttpResponse<IParamWilaya[]>;

@Injectable({ providedIn: 'root' })
export class ParamWilayaService {
  public resourceUrl = SERVER_API_URL + 'api/param-wilayas';

  constructor(protected http: HttpClient) {}

  create(paramWilaya: IParamWilaya): Observable<EntityResponseType> {
    return this.http.post<IParamWilaya>(this.resourceUrl, paramWilaya, { observe: 'response' });
  }

  update(paramWilaya: IParamWilaya): Observable<EntityResponseType> {
    return this.http.put<IParamWilaya>(this.resourceUrl, paramWilaya, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IParamWilaya>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IParamWilaya[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
