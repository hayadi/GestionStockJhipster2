import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ParamArticleService } from 'app/entities/param-article/param-article.service';
import { IParamArticle, ParamArticle } from 'app/shared/model/param-article.model';

describe('Service Tests', () => {
  describe('ParamArticle Service', () => {
    let injector: TestBed;
    let service: ParamArticleService;
    let httpMock: HttpTestingController;
    let elemDefault: IParamArticle;
    let expectedResult: IParamArticle | IParamArticle[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(ParamArticleService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new ParamArticle(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', false, 0, 0, 0, false);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a ParamArticle', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new ParamArticle()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a ParamArticle', () => {
        const returnedFromService = Object.assign(
          {
            code: 'BBBBBB',
            libelle: 'BBBBBB',
            description: 'BBBBBB',
            consommable: true,
            quantiteSeuil: 1,
            garantieSeuil: 1,
            expirationSeuil: 1,
            expirable: true,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of ParamArticle', () => {
        const returnedFromService = Object.assign(
          {
            code: 'BBBBBB',
            libelle: 'BBBBBB',
            description: 'BBBBBB',
            consommable: true,
            quantiteSeuil: 1,
            garantieSeuil: 1,
            expirationSeuil: 1,
            expirable: true,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a ParamArticle', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
