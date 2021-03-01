import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ParamFournisseurService } from 'app/entities/param-fournisseur/param-fournisseur.service';
import { IParamFournisseur, ParamFournisseur } from 'app/shared/model/param-fournisseur.model';

describe('Service Tests', () => {
  describe('ParamFournisseur Service', () => {
    let injector: TestBed;
    let service: ParamFournisseurService;
    let httpMock: HttpTestingController;
    let elemDefault: IParamFournisseur;
    let expectedResult: IParamFournisseur | IParamFournisseur[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(ParamFournisseurService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new ParamFournisseur(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a ParamFournisseur', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new ParamFournisseur()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a ParamFournisseur', () => {
        const returnedFromService = Object.assign(
          {
            code: 'BBBBBB',
            numeroRegistreCommerce: 'BBBBBB',
            nif: 'BBBBBB',
            nis: 'BBBBBB',
            numArtImposition: 'BBBBBB',
            raisonSociale: 'BBBBBB',
            telephone: 'BBBBBB',
            fax: 'BBBBBB',
            email: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of ParamFournisseur', () => {
        const returnedFromService = Object.assign(
          {
            code: 'BBBBBB',
            numeroRegistreCommerce: 'BBBBBB',
            nif: 'BBBBBB',
            nis: 'BBBBBB',
            numArtImposition: 'BBBBBB',
            raisonSociale: 'BBBBBB',
            telephone: 'BBBBBB',
            fax: 'BBBBBB',
            email: 'BBBBBB',
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

      it('should delete a ParamFournisseur', () => {
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
