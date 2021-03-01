export interface IParamFournisseur {
  id?: number;
  code?: string;
  numeroRegistreCommerce?: string;
  nif?: string;
  nis?: string;
  numArtImposition?: string;
  raisonSociale?: string;
  telephone?: string;
  fax?: string;
  email?: string;
}

export class ParamFournisseur implements IParamFournisseur {
  constructor(
    public id?: number,
    public code?: string,
    public numeroRegistreCommerce?: string,
    public nif?: string,
    public nis?: string,
    public numArtImposition?: string,
    public raisonSociale?: string,
    public telephone?: string,
    public fax?: string,
    public email?: string
  ) {}
}
