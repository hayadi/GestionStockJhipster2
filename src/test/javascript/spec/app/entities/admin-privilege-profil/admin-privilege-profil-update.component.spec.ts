import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { GestionStockJhipsterTestModule } from '../../../test.module';
import { AdminPrivilegeProfilUpdateComponent } from 'app/entities/admin-privilege-profil/admin-privilege-profil-update.component';
import { AdminPrivilegeProfilService } from 'app/entities/admin-privilege-profil/admin-privilege-profil.service';
import { AdminPrivilegeProfil } from 'app/shared/model/admin-privilege-profil.model';

describe('Component Tests', () => {
  describe('AdminPrivilegeProfil Management Update Component', () => {
    let comp: AdminPrivilegeProfilUpdateComponent;
    let fixture: ComponentFixture<AdminPrivilegeProfilUpdateComponent>;
    let service: AdminPrivilegeProfilService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionStockJhipsterTestModule],
        declarations: [AdminPrivilegeProfilUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(AdminPrivilegeProfilUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AdminPrivilegeProfilUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AdminPrivilegeProfilService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new AdminPrivilegeProfil(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new AdminPrivilegeProfil();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
