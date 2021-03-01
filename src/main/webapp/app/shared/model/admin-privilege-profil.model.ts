import { IAdminProfil } from 'app/shared/model/admin-profil.model';
import { IAdminPrivilege } from 'app/shared/model/admin-privilege.model';

export interface IAdminPrivilegeProfil {
  id?: number;
  profil?: IAdminProfil;
  privilege?: IAdminPrivilege;
}

export class AdminPrivilegeProfil implements IAdminPrivilegeProfil {
  constructor(public id?: number, public profil?: IAdminProfil, public privilege?: IAdminPrivilege) {}
}
