import { IParamArticle } from 'app/shared/model/param-article.model';

export interface IParamModelArticle {
  id?: number;
  code?: string;
  libelle?: string;
  urlImage?: string;
  article?: IParamArticle;
}

export class ParamModelArticle implements IParamModelArticle {
  constructor(
    public id?: number,
    public code?: string,
    public libelle?: string,
    public urlImage?: string,
    public article?: IParamArticle
  ) {}
}
