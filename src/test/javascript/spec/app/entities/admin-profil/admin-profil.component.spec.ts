import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GestionStockJhipsterTestModule } from '../../../test.module';
import { AdminProfilComponent } from 'app/entities/admin-profil/admin-profil.component';
import { AdminProfilService } from 'app/entities/admin-profil/admin-profil.service';
import { AdminProfil } from 'app/shared/model/admin-profil.model';

describe('Component Tests', () => {
  describe('AdminProfil Management Component', () => {
    let comp: AdminProfilComponent;
    let fixture: ComponentFixture<AdminProfilComponent>;
    let service: AdminProfilService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionStockJhipsterTestModule],
        declarations: [AdminProfilComponent],
      })
        .overrideTemplate(AdminProfilComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AdminProfilComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AdminProfilService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new AdminProfil(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.adminProfils && comp.adminProfils[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
