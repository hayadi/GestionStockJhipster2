import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { GestionStockJhipsterTestModule } from '../../../test.module';
import { AdminPrivilegeUpdateComponent } from 'app/entities/admin-privilege/admin-privilege-update.component';
import { AdminPrivilegeService } from 'app/entities/admin-privilege/admin-privilege.service';
import { AdminPrivilege } from 'app/shared/model/admin-privilege.model';

describe('Component Tests', () => {
  describe('AdminPrivilege Management Update Component', () => {
    let comp: AdminPrivilegeUpdateComponent;
    let fixture: ComponentFixture<AdminPrivilegeUpdateComponent>;
    let service: AdminPrivilegeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionStockJhipsterTestModule],
        declarations: [AdminPrivilegeUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(AdminPrivilegeUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AdminPrivilegeUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AdminPrivilegeService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new AdminPrivilege(123);
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
        const entity = new AdminPrivilege();
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
