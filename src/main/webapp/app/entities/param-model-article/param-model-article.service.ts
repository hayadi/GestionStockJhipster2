import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IParamModelArticle } from 'app/shared/model/param-model-article.model';

type EntityResponseType = HttpResponse<IParamModelArticle>;
type EntityArrayResponseType = HttpResponse<IParamModelArticle[]>;

@Injectable({ providedIn: 'root' })
export class ParamModelArticleService {
  public resourceUrl = SERVER_API_URL + 'api/param-model-articles';

  constructor(protected http: HttpClient) {}

  create(paramModelArticle: IParamModelArticle): Observable<EntityResponseType> {
    return this.http.post<IParamModelArticle>(this.resourceUrl, paramModelArticle, { observe: 'response' });
  }

  update(paramModelArticle: IParamModelArticle): Observable<EntityResponseType> {
    return this.http.put<IParamModelArticle>(this.resourceUrl, paramModelArticle, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IParamModelArticle>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IParamModelArticle[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
