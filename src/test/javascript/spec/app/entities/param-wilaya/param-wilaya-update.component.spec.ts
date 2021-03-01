import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { GestionStockJhipsterTestModule } from '../../../test.module';
import { ParamWilayaUpdateComponent } from 'app/entities/param-wilaya/param-wilaya-update.component';
import { ParamWilayaService } from 'app/entities/param-wilaya/param-wilaya.service';
import { ParamWilaya } from 'app/shared/model/param-wilaya.model';

describe('Component Tests', () => {
  describe('ParamWilaya Management Update Component', () => {
    let comp: ParamWilayaUpdateComponent;
    let fixture: ComponentFixture<ParamWilayaUpdateComponent>;
    let service: ParamWilayaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionStockJhipsterTestModule],
        declarations: [ParamWilayaUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ParamWilayaUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ParamWilayaUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ParamWilayaService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ParamWilaya(123);
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
        const entity = new ParamWilaya();
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
