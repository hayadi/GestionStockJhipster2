import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { GestionStockJhipsterTestModule } from '../../../test.module';
import { ParamModelArticleUpdateComponent } from 'app/entities/param-model-article/param-model-article-update.component';
import { ParamModelArticleService } from 'app/entities/param-model-article/param-model-article.service';
import { ParamModelArticle } from 'app/shared/model/param-model-article.model';

describe('Component Tests', () => {
  describe('ParamModelArticle Management Update Component', () => {
    let comp: ParamModelArticleUpdateComponent;
    let fixture: ComponentFixture<ParamModelArticleUpdateComponent>;
    let service: ParamModelArticleService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionStockJhipsterTestModule],
        declarations: [ParamModelArticleUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ParamModelArticleUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ParamModelArticleUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ParamModelArticleService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ParamModelArticle(123);
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
        const entity = new ParamModelArticle();
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
