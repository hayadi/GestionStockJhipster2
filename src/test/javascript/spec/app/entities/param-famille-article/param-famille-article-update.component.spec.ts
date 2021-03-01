import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { GestionStockJhipsterTestModule } from '../../../test.module';
import { ParamFamilleArticleUpdateComponent } from 'app/entities/param-famille-article/param-famille-article-update.component';
import { ParamFamilleArticleService } from 'app/entities/param-famille-article/param-famille-article.service';
import { ParamFamilleArticle } from 'app/shared/model/param-famille-article.model';

describe('Component Tests', () => {
  describe('ParamFamilleArticle Management Update Component', () => {
    let comp: ParamFamilleArticleUpdateComponent;
    let fixture: ComponentFixture<ParamFamilleArticleUpdateComponent>;
    let service: ParamFamilleArticleService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionStockJhipsterTestModule],
        declarations: [ParamFamilleArticleUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ParamFamilleArticleUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ParamFamilleArticleUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ParamFamilleArticleService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ParamFamilleArticle(123);
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
        const entity = new ParamFamilleArticle();
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
