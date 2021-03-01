import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IAdminProfil } from 'app/shared/model/admin-profil.model';

type EntityResponseType = HttpResponse<IAdminProfil>;
type EntityArrayResponseType = HttpResponse<IAdminProfil[]>;

@Injectable({ providedIn: 'root' })
export class AdminProfilService {
  public resourceUrl = SERVER_API_URL + 'api/admin-profils';

  constructor(protected http: HttpClient) {}

  create(adminProfil: IAdminProfil): Observable<EntityResponseType> {
    return this.http.post<IAdminProfil>(this.resourceUrl, adminProfil, { observe: 'response' });
  }

  update(adminProfil: IAdminProfil): Observable<EntityResponseType> {
    return this.http.put<IAdminProfil>(this.resourceUrl, adminProfil, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IAdminProfil>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IAdminProfil[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
