import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IParamFamilleArticle } from 'app/shared/model/param-famille-article.model';

type EntityResponseType = HttpResponse<IParamFamilleArticle>;
type EntityArrayResponseType = HttpResponse<IParamFamilleArticle[]>;

@Injectable({ providedIn: 'root' })
export class ParamFamilleArticleService {
  public resourceUrl = SERVER_API_URL + 'api/param-famille-articles';

  constructor(protected http: HttpClient) {}

  create(paramFamilleArticle: IParamFamilleArticle): Observable<EntityResponseType> {
    return this.http.post<IParamFamilleArticle>(this.resourceUrl, paramFamilleArticle, { observe: 'response' });
  }

  update(paramFamilleArticle: IParamFamilleArticle): Observable<EntityResponseType> {
    return this.http.put<IParamFamilleArticle>(this.resourceUrl, paramFamilleArticle, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IParamFamilleArticle>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IParamFamilleArticle[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
