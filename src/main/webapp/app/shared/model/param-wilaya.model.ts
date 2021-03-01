export interface IParamWilaya {
  id?: number;
  code?: string;
  libelle?: string;
}

export class ParamWilaya implements IParamWilaya {
  constructor(public id?: number, public code?: string, public libelle?: string) {}
}
