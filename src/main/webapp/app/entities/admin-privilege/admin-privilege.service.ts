import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IAdminPrivilege } from 'app/shared/model/admin-privilege.model';

type EntityResponseType = HttpResponse<IAdminPrivilege>;
type EntityArrayResponseType = HttpResponse<IAdminPrivilege[]>;

@Injectable({ providedIn: 'root' })
export class AdminPrivilegeService {
  public resourceUrl = SERVER_API_URL + 'api/admin-privileges';

  constructor(protected http: HttpClient) {}

  create(adminPrivilege: IAdminPrivilege): Observable<EntityResponseType> {
    return this.http.post<IAdminPrivilege>(this.resourceUrl, adminPrivilege, { observe: 'response' });
  }

  update(adminPrivilege: IAdminPrivilege): Observable<EntityResponseType> {
    return this.http.put<IAdminPrivilege>(this.resourceUrl, adminPrivilege, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IAdminPrivilege>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IAdminPrivilege[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
