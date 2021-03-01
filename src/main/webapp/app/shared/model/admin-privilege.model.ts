export interface IAdminPrivilege {
  id?: number;
  code?: string;
  libelle?: string;
  description?: string;
}

export class AdminPrivilege implements IAdminPrivilege {
  constructor(public id?: number, public code?: string, public libelle?: string, public description?: string) {}
}
