import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { GestionStockJhipsterTestModule } from '../../../test.module';
import { ParamCommuneUpdateComponent } from 'app/entities/param-commune/param-commune-update.component';
import { ParamCommuneService } from 'app/entities/param-commune/param-commune.service';
import { ParamCommune } from 'app/shared/model/param-commune.model';

describe('Component Tests', () => {
  describe('ParamCommune Management Update Component', () => {
    let comp: ParamCommuneUpdateComponent;
    let fixture: ComponentFixture<ParamCommuneUpdateComponent>;
    let service: ParamCommuneService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionStockJhipsterTestModule],
        declarations: [ParamCommuneUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ParamCommuneUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ParamCommuneUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ParamCommuneService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ParamCommune(123);
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
        const entity = new ParamCommune();
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
