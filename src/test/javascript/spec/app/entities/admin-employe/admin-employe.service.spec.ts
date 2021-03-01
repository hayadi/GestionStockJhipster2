import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { AdminEmployeService } from 'app/entities/admin-employe/admin-employe.service';
import { IAdminEmploye, AdminEmploye } from 'app/shared/model/admin-employe.model';

describe('Service Tests', () => {
  describe('AdminEmploye Service', () => {
    let injector: TestBed;
    let service: AdminEmployeService;
    let httpMock: HttpTestingController;
    let elemDefault: IAdminEmploye;
    let expectedResult: IAdminEmploye | IAdminEmploye[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(AdminEmployeService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new AdminEmploye(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        currentDate,
        'AAAAAAA',
        false,
        'AAAAAAA',
        currentDate
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dateNaissance: currentDate.format(DATE_FORMAT),
            dateIntegration: currentDate.format(DATE_FORMAT),
            dateEntree: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a AdminEmploye', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dateNaissance: currentDate.format(DATE_FORMAT),
            dateIntegration: currentDate.format(DATE_FORMAT),
            dateEntree: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateNaissance: currentDate,
            dateIntegration: currentDate,
            dateEntree: currentDate,
          },
          returnedFromService
        );

        service.create(new AdminEmploye()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a AdminEmploye', () => {
        const returnedFromService = Object.assign(
          {
            code: 'BBBBBB',
            login: 'BBBBBB',
            nom: 'BBBBBB',
            prenom: 'BBBBBB',
            email: 'BBBBBB',
            dateNaissance: currentDate.format(DATE_FORMAT),
            dateIntegration: currentDate.format(DATE_FORMAT),
            adresse: 'BBBBBB',
            utilisateur: true,
            password: 'BBBBBB',
            dateEntree: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateNaissance: currentDate,
            dateIntegration: currentDate,
            dateEntree: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of AdminEmploye', () => {
        const returnedFromService = Object.assign(
          {
            code: 'BBBBBB',
            login: 'BBBBBB',
            nom: 'BBBBBB',
            prenom: 'BBBBBB',
            email: 'BBBBBB',
            dateNaissance: currentDate.format(DATE_FORMAT),
            dateIntegration: currentDate.format(DATE_FORMAT),
            adresse: 'BBBBBB',
            utilisateur: true,
            password: 'BBBBBB',
            dateEntree: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateNaissance: currentDate,
            dateIntegration: currentDate,
            dateEntree: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a AdminEmploye', () => {
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
