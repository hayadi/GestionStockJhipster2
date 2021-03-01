export interface IParamUniteMesure {
  id?: number;
  code?: string;
  libelle?: string;
}

export class ParamUniteMesure implements IParamUniteMesure {
  constructor(public id?: number, public code?: string, public libelle?: string) {}
}
