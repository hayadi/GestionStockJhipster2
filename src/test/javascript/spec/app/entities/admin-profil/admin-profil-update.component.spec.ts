import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { GestionStockJhipsterTestModule } from '../../../test.module';
import { AdminProfilUpdateComponent } from 'app/entities/admin-profil/admin-profil-update.component';
import { AdminProfilService } from 'app/entities/admin-profil/admin-profil.service';
import { AdminProfil } from 'app/shared/model/admin-profil.model';

describe('Component Tests', () => {
  describe('AdminProfil Management Update Component', () => {
    let comp: AdminProfilUpdateComponent;
    let fixture: ComponentFixture<AdminProfilUpdateComponent>;
    let service: AdminProfilService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionStockJhipsterTestModule],
        declarations: [AdminProfilUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(AdminProfilUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AdminProfilUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AdminProfilService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new AdminProfil(123);
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
        const entity = new AdminProfil();
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
