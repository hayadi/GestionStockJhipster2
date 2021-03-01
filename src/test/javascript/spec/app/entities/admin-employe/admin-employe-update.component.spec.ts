import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { GestionStockJhipsterTestModule } from '../../../test.module';
import { AdminEmployeUpdateComponent } from 'app/entities/admin-employe/admin-employe-update.component';
import { AdminEmployeService } from 'app/entities/admin-employe/admin-employe.service';
import { AdminEmploye } from 'app/shared/model/admin-employe.model';

describe('Component Tests', () => {
  describe('AdminEmploye Management Update Component', () => {
    let comp: AdminEmployeUpdateComponent;
    let fixture: ComponentFixture<AdminEmployeUpdateComponent>;
    let service: AdminEmployeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionStockJhipsterTestModule],
        declarations: [AdminEmployeUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(AdminEmployeUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AdminEmployeUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AdminEmployeService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new AdminEmploye(123);
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
        const entity = new AdminEmploye();
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
