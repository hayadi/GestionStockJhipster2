import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { GestionStockJhipsterTestModule } from '../../../test.module';
import { ParamUniteMesureUpdateComponent } from 'app/entities/param-unite-mesure/param-unite-mesure-update.component';
import { ParamUniteMesureService } from 'app/entities/param-unite-mesure/param-unite-mesure.service';
import { ParamUniteMesure } from 'app/shared/model/param-unite-mesure.model';

describe('Component Tests', () => {
  describe('ParamUniteMesure Management Update Component', () => {
    let comp: ParamUniteMesureUpdateComponent;
    let fixture: ComponentFixture<ParamUniteMesureUpdateComponent>;
    let service: ParamUniteMesureService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionStockJhipsterTestModule],
        declarations: [ParamUniteMesureUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ParamUniteMesureUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ParamUniteMesureUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ParamUniteMesureService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ParamUniteMesure(123);
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
        const entity = new ParamUniteMesure();
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
