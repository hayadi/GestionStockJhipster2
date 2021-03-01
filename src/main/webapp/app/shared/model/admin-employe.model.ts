import { Moment } from 'moment';
import { IAdminProfil } from 'app/shared/model/admin-profil.model';

export interface IAdminEmploye {
  id?: number;
  code?: string;
  login?: string;
  nom?: string;
  prenom?: string;
  email?: string;
  dateNaissance?: Moment;
  dateIntegration?: Moment;
  adresse?: string;
  utilisateur?: boolean;
  password?: string;
  dateEntree?: Moment;
  profil?: IAdminProfil;
}

export class AdminEmploye implements IAdminEmploye {
  constructor(
    public id?: number,
    public code?: string,
    public login?: string,
    public nom?: string,
    public prenom?: string,
    public email?: string,
    public dateNaissance?: Moment,
    public dateIntegration?: Moment,
    public adresse?: string,
    public utilisateur?: boolean,
    public password?: string,
    public dateEntree?: Moment,
    public profil?: IAdminProfil
  ) {
    this.utilisateur = this.utilisateur || false;
  }
}
