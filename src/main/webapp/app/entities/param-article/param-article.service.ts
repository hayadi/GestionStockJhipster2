import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IParamArticle } from 'app/shared/model/param-article.model';

type EntityResponseType = HttpResponse<IParamArticle>;
type EntityArrayResponseType = HttpResponse<IParamArticle[]>;

@Injectable({ providedIn: 'root' })
export class ParamArticleService {
  public resourceUrl = SERVER_API_URL + 'api/param-articles';

  constructor(protected http: HttpClient) {}

  create(paramArticle: IParamArticle): Observable<EntityResponseType> {
    return this.http.post<IParamArticle>(this.resourceUrl, paramArticle, { observe: 'response' });
  }

  update(paramArticle: IParamArticle): Observable<EntityResponseType> {
    return this.http.put<IParamArticle>(this.resourceUrl, paramArticle, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IParamArticle>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IParamArticle[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
