import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GestionStockJhipsterTestModule } from '../../../test.module';
import { ParamFamilleArticleComponent } from 'app/entities/param-famille-article/param-famille-article.component';
import { ParamFamilleArticleService } from 'app/entities/param-famille-article/param-famille-article.service';
import { ParamFamilleArticle } from 'app/shared/model/param-famille-article.model';

describe('Component Tests', () => {
  describe('ParamFamilleArticle Management Component', () => {
    let comp: ParamFamilleArticleComponent;
    let fixture: ComponentFixture<ParamFamilleArticleComponent>;
    let service: ParamFamilleArticleService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionStockJhipsterTestModule],
        declarations: [ParamFamilleArticleComponent],
      })
        .overrideTemplate(ParamFamilleArticleComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ParamFamilleArticleComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ParamFamilleArticleService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ParamFamilleArticle(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.paramFamilleArticles && comp.paramFamilleArticles[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
