import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GestionStockJhipsterTestModule } from '../../../test.module';
import { AdminPrivilegeProfilComponent } from 'app/entities/admin-privilege-profil/admin-privilege-profil.component';
import { AdminPrivilegeProfilService } from 'app/entities/admin-privilege-profil/admin-privilege-profil.service';
import { AdminPrivilegeProfil } from 'app/shared/model/admin-privilege-profil.model';

describe('Component Tests', () => {
  describe('AdminPrivilegeProfil Management Component', () => {
    let comp: AdminPrivilegeProfilComponent;
    let fixture: ComponentFixture<AdminPrivilegeProfilComponent>;
    let service: AdminPrivilegeProfilService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionStockJhipsterTestModule],
        declarations: [AdminPrivilegeProfilComponent],
      })
        .overrideTemplate(AdminPrivilegeProfilComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AdminPrivilegeProfilComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AdminPrivilegeProfilService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new AdminPrivilegeProfil(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.adminPrivilegeProfils && comp.adminPrivilegeProfils[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
