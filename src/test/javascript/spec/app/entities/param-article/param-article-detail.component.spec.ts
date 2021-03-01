import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GestionStockJhipsterTestModule } from '../../../test.module';
import { ParamArticleDetailComponent } from 'app/entities/param-article/param-article-detail.component';
import { ParamArticle } from 'app/shared/model/param-article.model';

describe('Component Tests', () => {
  describe('ParamArticle Management Detail Component', () => {
    let comp: ParamArticleDetailComponent;
    let fixture: ComponentFixture<ParamArticleDetailComponent>;
    const route = ({ data: of({ paramArticle: new ParamArticle(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionStockJhipsterTestModule],
        declarations: [ParamArticleDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ParamArticleDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ParamArticleDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load paramArticle on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.paramArticle).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
