import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { AdminPrivilegeProfilService } from 'app/entities/admin-privilege-profil/admin-privilege-profil.service';
import { IAdminPrivilegeProfil, AdminPrivilegeProfil } from 'app/shared/model/admin-privilege-profil.model';

describe('Service Tests', () => {
  describe('AdminPrivilegeProfil Service', () => {
    let injector: TestBed;
    let service: AdminPrivilegeProfilService;
    let httpMock: HttpTestingController;
    let elemDefault: IAdminPrivilegeProfil;
    let expectedResult: IAdminPrivilegeProfil | IAdminPrivilegeProfil[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(AdminPrivilegeProfilService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new AdminPrivilegeProfil(0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a AdminPrivilegeProfil', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new AdminPrivilegeProfil()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a AdminPrivilegeProfil', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of AdminPrivilegeProfil', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a AdminPrivilegeProfil', () => {
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
