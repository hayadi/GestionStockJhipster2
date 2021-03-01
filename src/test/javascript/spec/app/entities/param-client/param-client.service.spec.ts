import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ParamClientService } from 'app/entities/param-client/param-client.service';
import { IParamClient, ParamClient } from 'app/shared/model/param-client.model';

describe('Service Tests', () => {
  describe('ParamClient Service', () => {
    let injector: TestBed;
    let service: ParamClientService;
    let httpMock: HttpTestingController;
    let elemDefault: IParamClient;
    let expectedResult: IParamClient | IParamClient[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(ParamClientService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new ParamClient(
        0,
        'AAAAAAA',
        'AAAAAAA',
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

      it('should create a ParamClient', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new ParamClient()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a ParamClient', () => {
        const returnedFromService = Object.assign(
          {
            code: 'BBBBBB',
            nom: 'BBBBBB',
            prenom: 'BBBBBB',
            raisonSociale: 'BBBBBB',
            numeroRegistreCommerce: 'BBBBBB',
            nif: 'BBBBBB',
            nis: 'BBBBBB',
            numArtImposition: 'BBBBBB',
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

      it('should return a list of ParamClient', () => {
        const returnedFromService = Object.assign(
          {
            code: 'BBBBBB',
            nom: 'BBBBBB',
            prenom: 'BBBBBB',
            raisonSociale: 'BBBBBB',
            numeroRegistreCommerce: 'BBBBBB',
            nif: 'BBBBBB',
            nis: 'BBBBBB',
            numArtImposition: 'BBBBBB',
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

      it('should delete a ParamClient', () => {
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
