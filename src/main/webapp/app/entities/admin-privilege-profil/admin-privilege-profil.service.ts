import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IAdminPrivilegeProfil } from 'app/shared/model/admin-privilege-profil.model';

type EntityResponseType = HttpResponse<IAdminPrivilegeProfil>;
type EntityArrayResponseType = HttpResponse<IAdminPrivilegeProfil[]>;

@Injectable({ providedIn: 'root' })
export class AdminPrivilegeProfilService {
  public resourceUrl = SERVER_API_URL + 'api/admin-privilege-profils';

  constructor(protected http: HttpClient) {}

  create(adminPrivilegeProfil: IAdminPrivilegeProfil): Observable<EntityResponseType> {
    return this.http.post<IAdminPrivilegeProfil>(this.resourceUrl, adminPrivilegeProfil, { observe: 'response' });
  }

  update(adminPrivilegeProfil: IAdminPrivilegeProfil): Observable<EntityResponseType> {
    return this.http.put<IAdminPrivilegeProfil>(this.resourceUrl, adminPrivilegeProfil, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IAdminPrivilegeProfil>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IAdminPrivilegeProfil[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
