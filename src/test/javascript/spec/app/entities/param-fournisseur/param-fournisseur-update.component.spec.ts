import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { GestionStockJhipsterTestModule } from '../../../test.module';
import { ParamFournisseurUpdateComponent } from 'app/entities/param-fournisseur/param-fournisseur-update.component';
import { ParamFournisseurService } from 'app/entities/param-fournisseur/param-fournisseur.service';
import { ParamFournisseur } from 'app/shared/model/param-fournisseur.model';

describe('Component Tests', () => {
  describe('ParamFournisseur Management Update Component', () => {
    let comp: ParamFournisseurUpdateComponent;
    let fixture: ComponentFixture<ParamFournisseurUpdateComponent>;
    let service: ParamFournisseurService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionStockJhipsterTestModule],
        declarations: [ParamFournisseurUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ParamFournisseurUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ParamFournisseurUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ParamFournisseurService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ParamFournisseur(123);
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
        const entity = new ParamFournisseur();
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
