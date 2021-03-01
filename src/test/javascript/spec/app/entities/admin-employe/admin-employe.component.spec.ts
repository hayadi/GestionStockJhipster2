import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GestionStockJhipsterTestModule } from '../../../test.module';
import { AdminEmployeComponent } from 'app/entities/admin-employe/admin-employe.component';
import { AdminEmployeService } from 'app/entities/admin-employe/admin-employe.service';
import { AdminEmploye } from 'app/shared/model/admin-employe.model';

describe('Component Tests', () => {
  describe('AdminEmploye Management Component', () => {
    let comp: AdminEmployeComponent;
    let fixture: ComponentFixture<AdminEmployeComponent>;
    let service: AdminEmployeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionStockJhipsterTestModule],
        declarations: [AdminEmployeComponent],
      })
        .overrideTemplate(AdminEmployeComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AdminEmployeComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AdminEmployeService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new AdminEmploye(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.adminEmployes && comp.adminEmployes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
