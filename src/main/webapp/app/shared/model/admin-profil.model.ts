export interface IAdminProfil {
  id?: number;
  code?: string;
  libelle?: string;
  description?: string;
}

export class AdminProfil implements IAdminProfil {
  constructor(public id?: number, public code?: string, public libelle?: string, public description?: string) {}
}
