import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { GestionStockJhipsterTestModule } from '../../../test.module';
import { ParamArticleUpdateComponent } from 'app/entities/param-article/param-article-update.component';
import { ParamArticleService } from 'app/entities/param-article/param-article.service';
import { ParamArticle } from 'app/shared/model/param-article.model';

describe('Component Tests', () => {
  describe('ParamArticle Management Update Component', () => {
    let comp: ParamArticleUpdateComponent;
    let fixture: ComponentFixture<ParamArticleUpdateComponent>;
    let service: ParamArticleService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionStockJhipsterTestModule],
        declarations: [ParamArticleUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ParamArticleUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ParamArticleUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ParamArticleService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ParamArticle(123);
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
        const entity = new ParamArticle();
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
