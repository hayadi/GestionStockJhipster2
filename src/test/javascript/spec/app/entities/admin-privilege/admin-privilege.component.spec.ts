import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GestionStockJhipsterTestModule } from '../../../test.module';
import { AdminPrivilegeComponent } from 'app/entities/admin-privilege/admin-privilege.component';
import { AdminPrivilegeService } from 'app/entities/admin-privilege/admin-privilege.service';
import { AdminPrivilege } from 'app/shared/model/admin-privilege.model';

describe('Component Tests', () => {
  describe('AdminPrivilege Management Component', () => {
    let comp: AdminPrivilegeComponent;
    let fixture: ComponentFixture<AdminPrivilegeComponent>;
    let service: AdminPrivilegeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionStockJhipsterTestModule],
        declarations: [AdminPrivilegeComponent],
      })
        .overrideTemplate(AdminPrivilegeComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AdminPrivilegeComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AdminPrivilegeService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new AdminPrivilege(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.adminPrivileges && comp.adminPrivileges[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
