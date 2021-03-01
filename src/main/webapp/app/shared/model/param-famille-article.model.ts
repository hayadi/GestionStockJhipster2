export interface IParamFamilleArticle {
  id?: number;
  code?: string;
  libelle?: string;
}

export class ParamFamilleArticle implements IParamFamilleArticle {
  constructor(public id?: number, public code?: string, public libelle?: string) {}
}
