import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { GestionStockJhipsterTestModule } from '../../../test.module';
import { ParamClientUpdateComponent } from 'app/entities/param-client/param-client-update.component';
import { ParamClientService } from 'app/entities/param-client/param-client.service';
import { ParamClient } from 'app/shared/model/param-client.model';

describe('Component Tests', () => {
  describe('ParamClient Management Update Component', () => {
    let comp: ParamClientUpdateComponent;
    let fixture: ComponentFixture<ParamClientUpdateComponent>;
    let service: ParamClientService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionStockJhipsterTestModule],
        declarations: [ParamClientUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ParamClientUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ParamClientUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ParamClientService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ParamClient(123);
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
        const entity = new ParamClient();
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
