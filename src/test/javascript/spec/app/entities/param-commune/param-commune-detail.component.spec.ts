import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GestionStockJhipsterTestModule } from '../../../test.module';
import { ParamCommuneDetailComponent } from 'app/entities/param-commune/param-commune-detail.component';
import { ParamCommune } from 'app/shared/model/param-commune.model';

describe('Component Tests', () => {
  describe('ParamCommune Management Detail Component', () => {
    let comp: ParamCommuneDetailComponent;
    let fixture: ComponentFixture<ParamCommuneDetailComponent>;
    const route = ({ data: of({ paramCommune: new ParamCommune(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionStockJhipsterTestModule],
        declarations: [ParamCommuneDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ParamCommuneDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ParamCommuneDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load paramCommune on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.paramCommune).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
