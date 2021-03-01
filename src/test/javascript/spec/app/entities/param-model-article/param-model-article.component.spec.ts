import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GestionStockJhipsterTestModule } from '../../../test.module';
import { ParamModelArticleComponent } from 'app/entities/param-model-article/param-model-article.component';
import { ParamModelArticleService } from 'app/entities/param-model-article/param-model-article.service';
import { ParamModelArticle } from 'app/shared/model/param-model-article.model';

describe('Component Tests', () => {
  describe('ParamModelArticle Management Component', () => {
    let comp: ParamModelArticleComponent;
    let fixture: ComponentFixture<ParamModelArticleComponent>;
    let service: ParamModelArticleService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionStockJhipsterTestModule],
        declarations: [ParamModelArticleComponent],
      })
        .overrideTemplate(ParamModelArticleComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ParamModelArticleComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ParamModelArticleService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ParamModelArticle(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.paramModelArticles && comp.paramModelArticles[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
