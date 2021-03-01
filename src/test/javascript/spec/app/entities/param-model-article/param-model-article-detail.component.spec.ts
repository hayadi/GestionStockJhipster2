import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GestionStockJhipsterTestModule } from '../../../test.module';
import { ParamModelArticleDetailComponent } from 'app/entities/param-model-article/param-model-article-detail.component';
import { ParamModelArticle } from 'app/shared/model/param-model-article.model';

describe('Component Tests', () => {
  describe('ParamModelArticle Management Detail Component', () => {
    let comp: ParamModelArticleDetailComponent;
    let fixture: ComponentFixture<ParamModelArticleDetailComponent>;
    const route = ({ data: of({ paramModelArticle: new ParamModelArticle(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionStockJhipsterTestModule],
        declarations: [ParamModelArticleDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ParamModelArticleDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ParamModelArticleDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load paramModelArticle on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.paramModelArticle).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
