import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GestionStockJhipsterTestModule } from '../../../test.module';
import { ParamArticleComponent } from 'app/entities/param-article/param-article.component';
import { ParamArticleService } from 'app/entities/param-article/param-article.service';
import { ParamArticle } from 'app/shared/model/param-article.model';

describe('Component Tests', () => {
  describe('ParamArticle Management Component', () => {
    let comp: ParamArticleComponent;
    let fixture: ComponentFixture<ParamArticleComponent>;
    let service: ParamArticleService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionStockJhipsterTestModule],
        declarations: [ParamArticleComponent],
      })
        .overrideTemplate(ParamArticleComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ParamArticleComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ParamArticleService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ParamArticle(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.paramArticles && comp.paramArticles[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
