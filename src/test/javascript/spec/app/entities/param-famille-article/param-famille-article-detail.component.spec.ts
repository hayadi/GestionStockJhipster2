import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GestionStockJhipsterTestModule } from '../../../test.module';
import { ParamFamilleArticleDetailComponent } from 'app/entities/param-famille-article/param-famille-article-detail.component';
import { ParamFamilleArticle } from 'app/shared/model/param-famille-article.model';

describe('Component Tests', () => {
  describe('ParamFamilleArticle Management Detail Component', () => {
    let comp: ParamFamilleArticleDetailComponent;
    let fixture: ComponentFixture<ParamFamilleArticleDetailComponent>;
    const route = ({ data: of({ paramFamilleArticle: new ParamFamilleArticle(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionStockJhipsterTestModule],
        declarations: [ParamFamilleArticleDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ParamFamilleArticleDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ParamFamilleArticleDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load paramFamilleArticle on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.paramFamilleArticle).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
