import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IParamClient } from 'app/shared/model/param-client.model';

type EntityResponseType = HttpResponse<IParamClient>;
type EntityArrayResponseType = HttpResponse<IParamClient[]>;

@Injectable({ providedIn: 'root' })
export class ParamClientService {
  public resourceUrl = SERVER_API_URL + 'api/param-clients';

  constructor(protected http: HttpClient) {}

  create(paramClient: IParamClient): Observable<EntityResponseType> {
    return this.http.post<IParamClient>(this.resourceUrl, paramClient, { observe: 'response' });
  }

  update(paramClient: IParamClient): Observable<EntityResponseType> {
    return this.http.put<IParamClient>(this.resourceUrl, paramClient, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IParamClient>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IParamClient[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
