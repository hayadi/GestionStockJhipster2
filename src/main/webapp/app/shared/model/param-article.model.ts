import { IParamFamilleArticle } from 'app/shared/model/param-famille-article.model';
import { IParamUniteMesure } from 'app/shared/model/param-unite-mesure.model';

export interface IParamArticle {
  id?: number;
  code?: string;
  libelle?: string;
  description?: string;
  consommable?: boolean;
  quantiteSeuil?: number;
  garantieSeuil?: number;
  expirationSeuil?: number;
  expirable?: boolean;
  famille?: IParamFamilleArticle;
  unite?: IParamUniteMesure;
}

export class ParamArticle implements IParamArticle {
  constructor(
    public id?: number,
    public code?: string,
    public libelle?: string,
    public description?: string,
    public consommable?: boolean,
    public quantiteSeuil?: number,
    public garantieSeuil?: number,
    public expirationSeuil?: number,
    public expirable?: boolean,
    public famille?: IParamFamilleArticle,
    public unite?: IParamUniteMesure
  ) {
    this.consommable = this.consommable || false;
    this.expirable = this.expirable || false;
  }
}
