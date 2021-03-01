export interface IParamClient {
  id?: number;
  code?: string;
  nom?: string;
  prenom?: string;
  raisonSociale?: string;
  numeroRegistreCommerce?: string;
  nif?: string;
  nis?: string;
  numArtImposition?: string;
  telephone?: string;
  fax?: string;
  email?: string;
}

export class ParamClient implements IParamClient {
  constructor(
    public id?: number,
    public code?: string,
    public nom?: string,
    public prenom?: string,
    public raisonSociale?: string,
    public numeroRegistreCommerce?: string,
    public nif?: string,
    public nis?: string,
    public numArtImposition?: string,
    public telephone?: string,
    public fax?: string,
    public email?: string
  ) {}
}
